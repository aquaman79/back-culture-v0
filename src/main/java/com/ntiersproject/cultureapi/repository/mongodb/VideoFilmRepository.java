package com.ntiersproject.cultureapi.repository.mongodb;

import com.ntiersproject.cultureapi.model.dto.VideoFilm;
import com.ntiersproject.cultureapi.repository.mongodb.entity.VideoFilmEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoFilmRepository extends MongoRepository<VideoFilmEntity, String> {
    boolean existsByNom(String nom);

    VideoFilmEntity findByIdFilmAndIsBandeAnnonce(Long idFilm, Boolean isBandeAnnonce);
}
