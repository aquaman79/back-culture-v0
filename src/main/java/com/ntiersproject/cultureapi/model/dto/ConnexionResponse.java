package com.ntiersproject.cultureapi.model.dto;

import lombok.Data;

@Data
public class ConnexionResponse {
    private String jetonJWT;

    private Utilisateur utilisateur;
}
