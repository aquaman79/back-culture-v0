package com.ntiersproject.cultureapi.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Film {
    private Long id;

    private String titre;

    private String description;

    private LocalDate dateSortie;

    private Float duree;

    private String imageBase64;

    private String videoBase64;

    private String videoBandeAnnonceBase64;
}
