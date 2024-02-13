package com.ntiersproject.cultureapi.utils;

import java.lang.reflect.Field;

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
}
