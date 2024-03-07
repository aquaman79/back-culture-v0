package com.ntiersproject.cultureapi.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Commentaire {
    private Long id;

    private String auteur;

    private String contenu;

    private LocalDate date;
}
