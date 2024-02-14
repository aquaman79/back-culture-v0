package com.ntiersproject.cultureapi.repository.mysql;

import com.ntiersproject.cultureapi.repository.mysql.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Long> {

    boolean existsByTitre(String titre);

    FilmEntity findByTitre(String titre);

    @Override
    List<FilmEntity> findAllById(Iterable<Long> ids);
}
