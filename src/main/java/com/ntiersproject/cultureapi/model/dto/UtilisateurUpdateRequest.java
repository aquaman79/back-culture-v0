package com.ntiersproject.cultureapi.model.dto;

import lombok.Data;

@Data
public class UtilisateurUpdateRequest {

    private String nom;

    private String pseudo;

    private String email;

    private String motDePasse;

    private String confirmationMotDePasse;
}
