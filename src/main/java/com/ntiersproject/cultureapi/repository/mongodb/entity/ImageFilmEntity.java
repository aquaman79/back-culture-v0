package com.ntiersproject.cultureapi.repository.mongodb.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "imageFilm")
@Data
public class ImageFilmEntity {

    @Indexed(unique = true)
    private Long idFilm;

    private byte[] bytes;
}
