package com.ntiersproject.cultureapi.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class Film {
    private Long id;

    private String titre;

    private List<Long> idsGenres;

    private String description;

    private String dateSortie;

    private Float duree;

    private ImageFilm image;

    private VideoFilm video;

    private VideoFilm videoBandeAnnonce;
}
