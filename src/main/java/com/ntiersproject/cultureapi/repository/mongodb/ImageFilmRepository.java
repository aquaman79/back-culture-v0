package com.ntiersproject.cultureapi.repository.mongodb;

import com.ntiersproject.cultureapi.repository.mongodb.entity.ImageFilmEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageFilmRepository extends MongoRepository<ImageFilmEntity, String> {
}
