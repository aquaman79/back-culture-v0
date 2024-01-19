package com.ntiersproject.cultureapi.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Utilisateur {

    private Integer id;
    private String nom;

    private String email;

    private String motDePasse;

    private LocalDate dateInscription;

    private Boolean isAdmin;
}
