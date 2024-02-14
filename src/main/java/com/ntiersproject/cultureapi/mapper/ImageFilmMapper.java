package com.ntiersproject.cultureapi.mapper;

import com.ntiersproject.cultureapi.model.dto.ImageFilm;
import com.ntiersproject.cultureapi.repository.mongodb.entity.ImageFilmEntity;

import java.util.Base64;

public class ImageFilmMapper {
    public static ImageFilm mapToDto(ImageFilmEntity entite) {
        if(entite == null) {
            return null;
        }

        ImageFilm dto = new ImageFilm();
        dto.setIdFilm(entite.getIdFilm());
        dto.setNom(entite.getNom());
        dto.setDataBase64(Base64.getEncoder().encodeToString(entite.getBytes()));

        return dto;
    }

    public static ImageFilmEntity mapToEntity(ImageFilm dto) {
        if(dto == null) {
            return null;
        }

        ImageFilmEntity entite = new ImageFilmEntity();
        entite.setIdFilm(dto.getIdFilm());
        entite.setNom(dto.getNom());
        entite.setBytes(Base64.getDecoder().decode(dto.getDataBase64()));

        return entite;
    }
}
