package com.ntiersproject.cultureapi.mapper;

import com.ntiersproject.cultureapi.model.dto.ConnexionResponse;
import com.ntiersproject.cultureapi.model.dto.Utilisateur;

public class AuthMapper {
    public static ConnexionResponse mapToConnexionResponse(String jetonJwt, Utilisateur utilisateur) {
        ConnexionResponse connexionResponse = new ConnexionResponse();
        connexionResponse.setJetonJWT(jetonJwt);
        connexionResponse.setUtilisateur(utilisateur);

        return connexionResponse;
    }
}
