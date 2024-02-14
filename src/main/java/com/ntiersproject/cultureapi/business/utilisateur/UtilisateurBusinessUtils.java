package com.ntiersproject.cultureapi.business.utilisateur;

import com.ntiersproject.cultureapi.exception.FunctionalException;
import com.ntiersproject.cultureapi.model.dto.ConnexionRequest;
import com.ntiersproject.cultureapi.model.dto.Utilisateur;
import com.ntiersproject.cultureapi.utils.Constantes;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public class UtilisateurBusinessUtils {


    public static void valideDonneesEnregistrement(Utilisateur utilisateur) {
        if(utilisateur == null) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "Aucune information n'a été saisie");
        }


        validePseudo(utilisateur.getPseudo());

        valideEmail(utilisateur.getEmail());

        valideMotDePasse(utilisateur.getMotDePasse());
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

    public static void valideNom(String nom) {
        if(!StringUtils.hasText(nom)) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "Le nom ne peut être vide");
        }
    }

    public static void validePseudo(String pseudo) {
        if (pseudo == null || !pseudo.matches(Constantes.REGEX_PSEUDO)) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "Le pseudo doit contenir au moins 4 caractères : alphanumériques, - ou _ ");
        }
    }

    public static void valideEmail(String email) {
        if (email == null || !email.matches(Constantes.REGEX_EMAIL)) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "L'adresse e-mail n'est pas valide");
        }
    }

    public static void valideMotDePasse(String motDePasse) {
        if(motDePasse == null || !motDePasse.matches(Constantes.REGEX_MOT_DE_PASSE)) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "Le mot de passe doit contenir au moins 8 caractères : minimum 1 majuscule, 1 minuscule, 1 chiffre, 1 caractere special parmi #?!@$%^&*-");
        }
    }

}
