package com.ntiersproject.cultureapi.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Utilisateur {

    private Integer id;

    private String nom;

    private String pseudo;

    private String email;

    private String motDePasse;

    private LocalDate dateInscription;

    private Boolean isAdmin;
}
