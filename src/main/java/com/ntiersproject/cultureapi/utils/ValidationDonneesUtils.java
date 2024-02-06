package com.ntiersproject.cultureapi.utils;

import com.ntiersproject.cultureapi.exception.FunctionalException;
import com.ntiersproject.cultureapi.model.dto.ConnexionRequest;
import com.ntiersproject.cultureapi.model.dto.InscriptionRequest;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public class ValidationDonneesUtils {

    private static final String REGEX_PSEUDO = "^[a-zA-Z0-9_-]{4,}$";

    private static final String REGEX_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    private static final String REGEX_MOT_DE_PASSE = "(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";

    public static void valideDonneesInscription(InscriptionRequest inscriptionRequest) {
        if(inscriptionRequest == null) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "Aucune information n'a été saisie");
        }

        if(!StringUtils.hasText(inscriptionRequest.getNom())) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "Le nom doit être saisi");
        }

        if(inscriptionRequest.getPseudo() == null || !inscriptionRequest.getPseudo().matches(REGEX_PSEUDO)) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "Le pseudo doit contenir au moins 4 caractères : alphanumériques, - ou _ ");
        }

        if(inscriptionRequest.getEmail() == null || !inscriptionRequest.getEmail().matches(REGEX_EMAIL)) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "L'adresse e-mail n'est pas valide");
        }

        if(inscriptionRequest.getMotDePasse() == null || !inscriptionRequest.getMotDePasse().matches(REGEX_MOT_DE_PASSE)) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "Le mot de passe doit contenir au moins 8 caractères : minimum 1 majuscule, 1 minuscule, 1 chiffre, 1 caractere special parmi #?!@$%^&*-");
        }

        if(!inscriptionRequest.getMotDePasse().equals(inscriptionRequest.getConfirmationMotDePasse())) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "Les mots de passe doivent être identiques");
        }
    }

    public static void valideDonneesConnexion(ConnexionRequest connexionRequest) {
        if(connexionRequest == null) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "Aucune information n'a été saisie");
        }

        if(!StringUtils.hasText(connexionRequest.getUsername())) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "Le pseudo ou l'adresse mail doit être saisi");
        }

        if(!StringUtils.hasText(connexionRequest.getMotDePasse())) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "Le mot de passe doit être saisi");
        }

    }
}
