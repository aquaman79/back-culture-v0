package com.ntiersproject.cultureapi.business.genre;

import com.ntiersproject.cultureapi.exception.FunctionalException;
import com.ntiersproject.cultureapi.model.dto.Genre;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public class GenreBusinessUtils {
    public static void valideDonneesEnregistrement(Genre genre) {
        if(genre == null || !StringUtils.hasText(genre.getLibelle())) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "Le libelle doit être renseigné");
        }
    }
}
