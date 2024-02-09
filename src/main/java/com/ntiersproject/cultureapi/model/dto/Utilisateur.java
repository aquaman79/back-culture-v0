package com.ntiersproject.cultureapi.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Utilisateur {

    private Long id;

    private String nom;

    private String pseudo;

    private String email;

    @JsonIgnore
    private String motDePasse;

    private LocalDate dateInscription;

    private Boolean isAdmin;

}
