package com.ntiersproject.cultureapi.repository.mysql;

import com.ntiersproject.cultureapi.repository.mysql.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
    boolean existsByLibelle(String libelle);

    @Override
    List<GenreEntity> findAllById(Iterable<Long> ids);
}
