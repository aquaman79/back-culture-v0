package com.ntiersproject.cultureapi.utils;

import com.ntiersproject.cultureapi.exception.FunctionalException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class FormatDonneesUtils {

    /**
     * Enleve les espaces autour des chaines d'un objet
     * @param object
     */
    public static void trimStrings(Object object) {
        if(object == null) {
            return;
        }
        // Recuperation de la classe de l'objet
        Class<?> clazz = object.getClass();

        // Parcours des champs de la classe
        for (Field field : clazz.getDeclaredFields()) {
            // Verifie si le champ est de type String
            if (field.getType() == String.class) {
                // Rend le champ accessible s'il est privé
                field.setAccessible(true);

                try {
                    // Recupere la valeur du champ
                    String value = (String) field.get(object);

                    // Si la valeur n'est pas nulle, applique trim()
                    if (value != null) {
                        field.set(object, value.trim());
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void valideDate(String date) {
        try {
            if(StringUtils.hasText(date)) {
                LocalDate localDate = LocalDate.parse(date);
            }
        } catch (DateTimeParseException e) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "La date doit être au format YYYY-MM-dd");
        }
    }

}
