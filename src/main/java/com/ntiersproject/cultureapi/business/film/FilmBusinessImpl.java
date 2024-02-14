package com.ntiersproject.cultureapi.business.film;

import com.ntiersproject.cultureapi.business.imagefilm.ImageFilmUtils;
import com.ntiersproject.cultureapi.business.videofilm.VideoFilmUtils;
import com.ntiersproject.cultureapi.exception.FunctionalException;
import com.ntiersproject.cultureapi.mapper.FilmMapper;
import com.ntiersproject.cultureapi.mapper.ImageFilmMapper;
import com.ntiersproject.cultureapi.mapper.VideoFilmMapper;
import com.ntiersproject.cultureapi.model.dto.Film;
import com.ntiersproject.cultureapi.model.dto.ImageFilm;
import com.ntiersproject.cultureapi.repository.mongodb.ImageFilmRepository;
import com.ntiersproject.cultureapi.repository.mongodb.VideoFilmRepository;
import com.ntiersproject.cultureapi.repository.mongodb.entity.ImageFilmEntity;
import com.ntiersproject.cultureapi.repository.mongodb.entity.VideoFilmEntity;
import com.ntiersproject.cultureapi.repository.mysql.FilmRepository;
import com.ntiersproject.cultureapi.repository.mysql.GenreRepository;
import com.ntiersproject.cultureapi.repository.mysql.entity.FilmEntity;
import com.ntiersproject.cultureapi.repository.mysql.entity.GenreEntity;
import com.ntiersproject.cultureapi.utils.Constantes;
import com.ntiersproject.cultureapi.utils.FormatDonneesUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class FilmBusinessImpl implements FilmBusiness {

    private FilmRepository filmRepository;

    private GenreRepository genreRepository;

    private ImageFilmRepository imageFilmRepository;

    private VideoFilmRepository videoFilmRepository;

    public FilmBusinessImpl(FilmRepository filmRepository, GenreRepository genreRepository, ImageFilmRepository imageFilmRepository, VideoFilmRepository videoFilmRepository) {
        this.filmRepository = filmRepository;
        this.genreRepository = genreRepository;
        this.imageFilmRepository = imageFilmRepository;
        this.videoFilmRepository = videoFilmRepository;
    }

    @Override
    @Transactional(transactionManager = "transactionManager") // si une RuntimeException est levee, il y aura un Rollback
    public Film createFilm(Film film) {
        try {
            // validation donnees
            FormatDonneesUtils.trimStrings(film);
            FilmBusinessUtils.valideDonneesEnregistrement(film);
            verifieTitreExistePas(film.getTitre());
            ImageFilmUtils.valideDonneesEnregistrement(film.getImage());
            VideoFilmUtils.valideDonneesEnregistrement(film.getVideo());
            film.getVideo().setIsBandeAnnonce(false);
            VideoFilmUtils.valideDonneesEnregistrementVideoBandeAnnonce(film.getVideoBandeAnnonce());
            film.getVideoBandeAnnonce().setIsBandeAnnonce(true);

            FilmEntity filmEntite = FilmMapper.mapToEntity(film);
            filmEntite.setId(null);
            filmEntite.setGenres(new ArrayList<>());

            // Associe les genres au film
            if(film.getIdsGenres() != null) {
                for(Long idGenre : film.getIdsGenres()) {
                    GenreEntity genreEntite = genreRepository.findById(idGenre).orElseThrow(() -> new FunctionalException(HttpStatus.BAD_REQUEST, "Aucun genre d'identifiant "+idGenre+" existe"));
                    filmEntite.getGenres().add(genreEntite);
                }
            }
            // Creation du film
            filmEntite = filmRepository.save(filmEntite);

            Film filmCree = FilmMapper.mapToDto(filmEntite);

            // preparation enregistrement image
            ImageFilmEntity imageEntite = ImageFilmMapper.mapToEntity(film.getImage());
            imageEntite.setIdFilm(filmCree.getId());
            if(imageFilmRepository.existsByNom(film.getImage().getNom())) {
                throw new FunctionalException(HttpStatus.CONFLICT, "Ce nom d'image existe déjà");
            }

            // preparation enregistrement video
            VideoFilmEntity videoEntite = VideoFilmMapper.mapToEntity(film.getVideo());
            videoEntite.setIdFilm(filmCree.getId());
            if(videoFilmRepository.existsByNom(film.getVideo().getNom())) {
                throw new FunctionalException(HttpStatus.CONFLICT, "Le nom de la vidéo existe déjà");
            }


            // preparation enregistrement de la video bande d'annonce
            VideoFilmEntity videoBAEntite = VideoFilmMapper.mapToEntity(film.getVideoBandeAnnonce());
            videoBAEntite.setIdFilm(filmCree.getId());
            if(videoFilmRepository.existsByNom(film.getVideoBandeAnnonce().getNom())) {
                throw new FunctionalException(HttpStatus.CONFLICT, "Le nom de la vidéo bande d'annonce existe déjà");
            }

            // enregistrement image
            imageEntite = imageFilmRepository.save(imageEntite);
            filmCree.setImage(ImageFilmMapper.mapToDto(imageEntite));

            // enregistrement video
            videoEntite.setIsBandeAnnonce(false);
            videoEntite = videoFilmRepository.save(videoEntite);
            filmCree.setVideo(VideoFilmMapper.mapToDto(videoEntite));

            // enregistrement video bande d'annonce
            videoBAEntite.setIsBandeAnnonce(true);
            videoBAEntite = videoFilmRepository.save(videoBAEntite);
            filmCree.setVideoBandeAnnonce(VideoFilmMapper.mapToDto(videoBAEntite));
            return filmCree;
        } catch (FunctionalException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Film> getAll() {
        List<FilmEntity> filmsEntites = filmRepository.findAll();
        List<Film> films = FilmMapper.mapToDtos(filmsEntites);

        for(Film film: films) {
            ImageFilmEntity imageFilmEntite = imageFilmRepository.findByIdFilm(film.getId());
            film.setImage(ImageFilmMapper.mapToDto(imageFilmEntite));

            VideoFilmEntity videoEntite = videoFilmRepository.findByIdFilmAndIsBandeAnnonce(film.getId(), false);
            film.setVideo(VideoFilmMapper.mapToDto(videoEntite));

            VideoFilmEntity videoBAEntite = videoFilmRepository.findByIdFilmAndIsBandeAnnonce(film.getId(), true);
            film.setVideoBandeAnnonce(VideoFilmMapper.mapToDto(videoBAEntite));
        }

        return films;

    }

    @Override
    public List<Film> getAllByIds(List<Long> ids) {
        List<FilmEntity> filmsEntites = filmRepository.findAllById(ids);
        List<Film> films = FilmMapper.mapToDtos(filmsEntites);

        for(Film film: films) {
            ImageFilmEntity imageFilmEntite = imageFilmRepository.findByIdFilm(film.getId());
            film.setImage(ImageFilmMapper.mapToDto(imageFilmEntite));

            VideoFilmEntity videoEntite = videoFilmRepository.findByIdFilmAndIsBandeAnnonce(film.getId(), false);
            film.setVideo(VideoFilmMapper.mapToDto(videoEntite));

            VideoFilmEntity videoBAEntite = videoFilmRepository.findByIdFilmAndIsBandeAnnonce(film.getId(), true);
            film.setVideoBandeAnnonce(VideoFilmMapper.mapToDto(videoBAEntite));
        }

        return films;
    }

    @Override
    public Film getById(Long id) {
        FilmEntity filmEntite = filmRepository.findById(id).orElseThrow(() -> new FunctionalException(HttpStatus.NOT_FOUND, Constantes.MESSAGE_FILM_NON_TROUVE));
        Film film = FilmMapper.mapToDto(filmEntite);

        ImageFilmEntity imageFilmEntite = imageFilmRepository.findByIdFilm(film.getId());
        film.setImage(ImageFilmMapper.mapToDto(imageFilmEntite));

        VideoFilmEntity videoEntite = videoFilmRepository.findByIdFilmAndIsBandeAnnonce(film.getId(), false);
        film.setVideo(VideoFilmMapper.mapToDto(videoEntite));

        VideoFilmEntity videoBAEntite = videoFilmRepository.findByIdFilmAndIsBandeAnnonce(film.getId(), true);
        film.setVideoBandeAnnonce(VideoFilmMapper.mapToDto(videoBAEntite));

        return film;
    }

    @Override
    public Film updateFilm(Long id, Film film) {
        FilmEntity filmEntite = filmRepository.findById(id).orElseThrow(() -> new FunctionalException(HttpStatus.NOT_FOUND, Constantes.MESSAGE_FILM_NON_TROUVE));

        if(film == null) {
            return FilmMapper.mapToDto(filmEntite);
        }

        FormatDonneesUtils.trimStrings(film);
        if(film.getTitre() != null) {
            FilmBusinessUtils.valideTitre(film.getTitre());
            filmEntite.setTitre(film.getTitre());
        }

        if(film.getIdsGenres() != null) {
            List<GenreEntity> genresEntites = new ArrayList<>();
            for(Long idGenre : film.getIdsGenres()) {
                GenreEntity genreEntite = genreRepository.findById(idGenre).orElseThrow(() -> new FunctionalException(HttpStatus.BAD_REQUEST, "Aucun genre d'identifiant "+idGenre+" existe"));
                genresEntites.add(genreEntite);
            }
            filmEntite.setGenres(genresEntites);
        }

        if(film.getDescription() != null) {
            filmEntite.setDescription(film.getDescription());
        }

        if(film.getDateSortie() != null) {
            FormatDonneesUtils.valideDate(film.getDateSortie());
            filmEntite.setDateSortie(film.getDateSortie());
        }

        if(film.getDuree() != null) {
            filmEntite.setDuree(film.getDuree());
        }

        filmEntite = filmRepository.save(filmEntite);
        return FilmMapper.mapToDto(filmEntite);
    }

    private void verifieTitreExistePas(String titre) {
        if(filmRepository.existsByTitre(titre)) {
            throw new FunctionalException(HttpStatus.CONFLICT, "Ce titre existe déjà");
        }
    }
}
