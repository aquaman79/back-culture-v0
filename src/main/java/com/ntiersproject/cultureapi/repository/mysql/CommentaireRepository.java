package com.ntiersproject.cultureapi.repository.mysql;

import com.ntiersproject.cultureapi.repository.mysql.entity.CommentaireEntity;
import com.ntiersproject.cultureapi.repository.mysql.entity.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentaireRepository extends JpaRepository<CommentaireEntity, Long> {
    List<CommentaireEntity> findAllByUtilisateur(UtilisateurEntity utilisateur);
}
