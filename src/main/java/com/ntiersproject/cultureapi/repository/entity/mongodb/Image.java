package com.ntiersproject.cultureapi.repository.entity.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Image {
    @Id
    private Integer idFilm;
    private String nom;

    private String base64;
}
