package com.ntiersproject.cultureapi.business.film;
import com.ntiersproject.cultureapi.exception.FunctionalException;
import com.ntiersproject.cultureapi.mapper.FilmMapper;
import com.ntiersproject.cultureapi.mapper.ImageFilmMapper;
import com.ntiersproject.cultureapi.model.dto.Film;
import com.ntiersproject.cultureapi.repository.mongodb.ImageFilmRepository;
import com.ntiersproject.cultureapi.repository.mongodb.entity.ImageFilmEntity;
import com.ntiersproject.cultureapi.repository.mysql.FilmRepository;
import com.ntiersproject.cultureapi.repository.mysql.entity.FilmEntity;
import com.ntiersproject.cultureapi.utils.Constantes;
import com.ntiersproject.cultureapi.utils.FormatDonneesUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmBusinessImpl implements FilmBusiness {

    private FilmRepository filmRepository;

    private ImageFilmRepository imageFilmRepository;

    public FilmBusinessImpl(FilmRepository filmRepository, ImageFilmRepository imageFilmRepository) {
        this.filmRepository = filmRepository;
        this.imageFilmRepository = imageFilmRepository;
    }

    @Override
    @Transactional(transactionManager = "transactionManager") // si une RuntimeException est levee, il y aura un Rollback
    public Film createFilm(Film film) {
        try {
            // validation donnees
            FormatDonneesUtils.trimStrings(film);
            FilmBusinessUtils.valideDonneesEnregistrement(film);
            verifieTitreExistePas(film.getTitre());

            FilmEntity filmEntite = FilmMapper.mapToEntity(film);
            filmEntite.setId(null);
            // Creation du film
            filmEntite = filmRepository.save(filmEntite);

            Film filmCree = FilmMapper.mapToDto(filmEntite);

            ImageFilmEntity imageEntite = ImageFilmMapper.mapToEntity(filmCree.getId(), film.getImageBase64());

            // enregistrement image
            imageEntite = imageFilmRepository.save(imageEntite);
            filmCree.setImageBase64(ImageFilmMapper.mapToDto(imageEntite));

            return filmCree;
        } catch (FunctionalException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(transactionManager = "transactionManager") // si une RuntimeException est levee, il y aura un Rollback
    public List<Film> createFilms(List<Film> films) {
        List<Film> filmsCrees = new ArrayList<>();
        if(films != null) {
            for(Film film: films) {
                try {
                    // validation donnees
                    FormatDonneesUtils.trimStrings(film);
                    FilmBusinessUtils.valideDonneesEnregistrement(film);
                    verifieTitreExistePas(film.getTitre());

                    FilmEntity filmEntite = FilmMapper.mapToEntity(film);
                    filmEntite.setId(null);
                    // Creation du film
                    filmEntite = filmRepository.save(filmEntite);

                    Film filmCree = FilmMapper.mapToDto(filmEntite);

                    ImageFilmEntity imageEntite = ImageFilmMapper.mapToEntity(filmCree.getId(), film.getImageBase64());

                    // enregistrement image
                    imageEntite = imageFilmRepository.save(imageEntite);
                    filmCree.setImageBase64(ImageFilmMapper.mapToDto(imageEntite));

                    filmsCrees.add(filmCree);
                } catch (FunctionalException e) {
                    throw e;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return filmsCrees;
    }

    @Override
    public List<Film> getAll() {
        List<FilmEntity> filmsEntites = filmRepository.findAll();
        List<Film> films = FilmMapper.mapToDtos(filmsEntites);

        for(Film film: films) {
            ImageFilmEntity imageFilmEntite = imageFilmRepository.findByIdFilm(film.getId());
            film.setImageBase64(ImageFilmMapper.mapToDto(imageFilmEntite));
        }

        return films;
    }

    @Override
    public List<Film> getAllByIds(List<Long> ids) {
        List<FilmEntity> filmsEntites = filmRepository.findAllById(ids);
        List<Film> films = FilmMapper.mapToDtos(filmsEntites);

        for(Film film: films) {
            ImageFilmEntity imageFilmEntite = imageFilmRepository.findByIdFilm(film.getId());
            film.setImageBase64(ImageFilmMapper.mapToDto(imageFilmEntite));
        }

        return films;
    }

    @Override
    public Film getById(Long id) {
        FilmEntity filmEntite = filmRepository.findById(id).orElseThrow(() -> new FunctionalException(HttpStatus.NOT_FOUND, Constantes.MESSAGE_FILM_NON_TROUVE));
        Film film = FilmMapper.mapToDto(filmEntite);

        ImageFilmEntity imageFilmEntite = imageFilmRepository.findByIdFilm(film.getId());
        film.setImageBase64(ImageFilmMapper.mapToDto(imageFilmEntite));

        return film;
    }

    @Override
    public Film updateFilm(Long id, Film film) {
        FilmEntity filmEntite = filmRepository.findById(id).orElseThrow(() -> new FunctionalException(HttpStatus.NOT_FOUND, Constantes.MESSAGE_FILM_NON_TROUVE));

        if(film == null) {
            return FilmMapper.mapToDto(filmEntite); // aucune modif a faire
        }

        if(film.getTitre() != null) {
            FilmBusinessUtils.valideTitre(film.getTitre());
            filmEntite.setTitre(film.getTitre());
        }

        if(film.getDescription() != null) {
            filmEntite.setDescription(film.getDescription());
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
