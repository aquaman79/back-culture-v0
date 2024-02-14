package com.ntiersproject.cultureapi.model.dto;

import lombok.Data;

@Data
public class ImageFilm {
    private Long idFilm;
    private String nom;

    private String dataBase64;
}
