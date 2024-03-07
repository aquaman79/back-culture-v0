package com.ntiersproject.cultureapi.mapper;

import com.ntiersproject.cultureapi.exception.FunctionalException;
import com.ntiersproject.cultureapi.repository.mongodb.entity.ImageFilmEntity;
import org.springframework.http.HttpStatus;

import java.util.Base64;

public class ImageFilmMapper {
    public static String mapToDto(ImageFilmEntity entite) {
        if(entite == null) {
            return null;
        }

        return Base64.getEncoder().encodeToString(entite.getBytes());
    }

    public static ImageFilmEntity mapToEntity(Long idFilm, String imageBase64) {

        ImageFilmEntity entite = new ImageFilmEntity();
        entite.setIdFilm(idFilm);
        try {
            entite.setBytes(Base64.getDecoder().decode(imageBase64));
        } catch (IllegalArgumentException e) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "L'image n'est pas en base64");
        }

        return entite;
    }
}
