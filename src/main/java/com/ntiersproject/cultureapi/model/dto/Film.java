package com.ntiersproject.cultureapi.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Film {
    private Long id;

    private String titre;

    private String genre;

    private String description;

    private LocalDate dateSortie;

    private Float duree;

    private String imageBase64;

    private String urlBandeAnnonce;

    private List<Commentaire> commentaires;
}
