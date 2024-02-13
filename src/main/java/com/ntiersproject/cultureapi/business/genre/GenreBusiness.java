package com.ntiersproject.cultureapi.business.genre;

import com.ntiersproject.cultureapi.model.dto.Genre;

import java.util.List;

public interface GenreBusiness {
    Genre createGenre(Genre genre);

    List<Genre> getAll();

    List<Genre> getById(List<Long> ids);

    Genre getById(Long id);

    Genre updateGenre(Long id, Genre genre);

    void deleteById(Long id);

    void deleteAll();
}
