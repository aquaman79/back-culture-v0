package com.ntiersproject.cultureapi.business.imagefilm;

import com.ntiersproject.cultureapi.exception.FunctionalException;
import com.ntiersproject.cultureapi.model.dto.ImageFilm;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public class ImageFilmUtils {
    public static void valideDonneesEnregistrement(ImageFilm imageFilm) {
        if(imageFilm == null) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "L'image doit être fournie");
        }



        if(!StringUtils.hasText(imageFilm.getDataBase64())) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "L'image en base64 doit être fournie");
        }

    }

    public static void valideNom(String nom) {
        if(!StringUtils.hasText(nom)) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "Le nom de l'image ne peut être vide");
        }
    }

    public static void valideDataBase64(String data) {
        if(!StringUtils.hasText(data)) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "L'image en base64 ne peut être vide");
        }
    }
}
