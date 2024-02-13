package com.ntiersproject.cultureapi.repository.mongodb.entity;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "videoFilm")
@CompoundIndexes({
        @CompoundIndex(name = "film_bande_annonce_unique", def = "{'idFilm': 1, 'isBandeAnnonce': 1}", unique = true)
})
public class VideoFilmEntity {
    @Indexed(unique = true)
    private Integer idFilm;

    private Boolean isBandeAnnonce;
    @Indexed(unique = true)
    private String nom;


    private byte[] bytes;
}
