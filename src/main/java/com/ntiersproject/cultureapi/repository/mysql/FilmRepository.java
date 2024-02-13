package com.ntiersproject.cultureapi.repository.mysql;

import com.ntiersproject.cultureapi.repository.mysql.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Long> {
    FilmEntity findByTitre(String titre);

    boolean existsByTitre(String titre);
}
