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

        FormatDonneesUtils.valideDate(film.getDateSortie());
    }

    public static void valideTitre(String titre) {
        if(!StringUtils.hasText(titre)) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "Le titre doit être renseigné");
        }
    }
}
