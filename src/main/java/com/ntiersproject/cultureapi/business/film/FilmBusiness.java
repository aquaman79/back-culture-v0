package com.ntiersproject.cultureapi.business.film;

import com.ntiersproject.cultureapi.model.dto.Film;
import java.util.List;

public interface FilmBusiness {
    Film createFilm(Film film);

    List<Film> createFilms(List<Film> films);

    List<Film> getAll();

    List<Film> getAllByIds(List<Long> ids);

    Film getById(Long id);

    Film updateFilm(Long idUtilisateur, Film film);
}

