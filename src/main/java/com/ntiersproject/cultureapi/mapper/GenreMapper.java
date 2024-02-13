package com.ntiersproject.cultureapi.mapper;

import com.ntiersproject.cultureapi.model.dto.Genre;
import com.ntiersproject.cultureapi.repository.mysql.entity.GenreEntity;

import java.util.ArrayList;
import java.util.List;

public class GenreMapper {
    public static Genre mapToDto(GenreEntity entity) {
        if(entity == null) {
            return null;
        }

        Genre dto = new Genre();
        dto.setId(entity.getId());
        dto.setLibelle(entity.getLibelle());

        return dto;
    }

    public static List<Genre> mapToDtos(List<GenreEntity> entites) {
        List<Genre> dtos = new ArrayList<>();
        for(GenreEntity entite : entites) {
            dtos.add(mapToDto(entite));
        }
        return dtos;
    }

    public static GenreEntity mapToEntity(Genre dto) {
        if(dto == null) {
            return null;
        }

        GenreEntity entity = new GenreEntity();
        entity.setId(dto.getId());
        entity.setLibelle(dto.getLibelle());

        return entity;
    }
}
