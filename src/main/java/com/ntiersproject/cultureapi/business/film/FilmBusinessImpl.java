package com.ntiersproject.cultureapi.business.film;

import com.ntiersproject.cultureapi.exception.FunctionalException;
import com.ntiersproject.cultureapi.mapper.FilmMapper;
import com.ntiersproject.cultureapi.model.dto.Film;
import com.ntiersproject.cultureapi.repository.mongodb.ImageFilmRepository;
import com.ntiersproject.cultureapi.repository.mongodb.entity.ImageFilmEntity;
import com.ntiersproject.cultureapi.repository.mysql.FilmRepository;
import com.ntiersproject.cultureapi.repository.mysql.entity.FilmEntity;
import com.ntiersproject.cultureapi.utils.FormatDonneesUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FilmBusinessImpl implements FilmBusiness {

    private FilmRepository filmRepository;

    private ImageFilmRepository imageFilmRepository;

    public FilmBusinessImpl(FilmRepository filmRepository, ImageFilmRepository imageFilmRepository) {
        this.filmRepository = filmRepository;
        this.imageFilmRepository = imageFilmRepository;
    }

    public FilmBusinessImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    @Transactional(transactionManager = "transactionManager")
    public Film createFilm(Film film) {
        try {
            FormatDonneesUtils.trimStrings(film);
            FilmBusinessUtils.valideDonneesEnregistrement(film);
            verifieTitreExistePas(film.getTitre());

            FilmEntity entite = FilmMapper.mapToEntity(film);
            entite.setId(null);
            entite = filmRepository.save(entite);

            Film filmCree = FilmMapper.mapToDto(entite);

            ImageFilmEntity imageFilmEntite = new ImageFilmEntity();
            imageFilmEntite.setIdFilm(filmCree.getId());
            //imageFilmEntite.setNom();


        } catch(Exception e) {
            throw new RuntimeException(e); // Ceci va automatiquement declencher un rollback;
        }

        return null;
    }

    private void verifieTitreExistePas(String titre) {
        if(filmRepository.existsByTitre(titre)) {
            throw new FunctionalException(HttpStatus.CONFLICT, "Ce titre existe déjà");
        }
    }
}
