package com.ntiersproject.cultureapi.repository.mongodb;

import com.ntiersproject.cultureapi.repository.mongodb.entity.ImageFilmEntity;
import com.ntiersproject.cultureapi.repository.mysql.entity.FilmEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageFilmRepository extends MongoRepository<ImageFilmEntity, String> {
    ImageFilmEntity findByIdFilm(Long idFilm);

}
