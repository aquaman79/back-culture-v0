package com.ntiersproject.cultureapi.repository.mysql;

import com.ntiersproject.cultureapi.repository.mysql.entity.FilmEntity;
import com.ntiersproject.cultureapi.repository.mysql.entity.PanierEntity;
import com.ntiersproject.cultureapi.repository.mysql.entity.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PanierRepository extends JpaRepository<PanierEntity, Long> {
    List<PanierEntity> findAllByUtilisateur(UtilisateurEntity utilisateur);

    boolean existsByFilmAndUtilisateur(FilmEntity film, UtilisateurEntity utilisateur);

    void deletePanierEntityByFilmAndUtilisateur(FilmEntity film, UtilisateurEntity utilisateur);
}
