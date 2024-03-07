package com.ntiersproject.cultureapi.business.film;

import com.ntiersproject.cultureapi.exception.FunctionalException;
import com.ntiersproject.cultureapi.model.dto.Film;
import com.ntiersproject.cultureapi.utils.FormatDonneesUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public class FilmBusinessUtils {
    public static void valideDonneesEnregistrement(Film film) {
        if(film == null) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "Aucune information n'a été saisie");
        }

        valideTitre(film.getTitre());

        valideGenre(film.getGenre());

        valideImageBase64(film.getImageBase64());

        valideUrlBandeAnnonce(film.getUrlBandeAnnonce());
    }

    public static void valideTitre(String titre) {
        if(!StringUtils.hasText(titre)) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "Le titre ne peut être vide");
        }
    }

    public static void valideGenre(String genre) {
        if(!StringUtils.hasText(genre)) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "Le genre ne peut être vide");
        }
    }
    public static void valideUrlBandeAnnonce(String urlBandeAnnonce) {
        if(!StringUtils.hasText(urlBandeAnnonce)) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "L'url de la bande d'annonce ne peut être vide");
        }
    }

    public static void valideImageBase64(String imageBase64) {
        if(!StringUtils.hasText(imageBase64)) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "L'image en base64 ne peut être vide");
        }
    }
}
