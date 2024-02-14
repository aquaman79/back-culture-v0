package com.ntiersproject.cultureapi.business.genre;

import com.ntiersproject.cultureapi.exception.FunctionalException;
import com.ntiersproject.cultureapi.model.dto.Genre;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public class GenreBusinessUtils {
    public static void valideDonneesEnregistrement(Genre genre) {
        if(genre == null) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "Aucune information n'a été renseignée");
        }
    }

    public static void valideLibelle(String libelle) {
        if(!StringUtils.hasText(libelle)) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "Le libellé ne peut être vide");
        }
    }
}
