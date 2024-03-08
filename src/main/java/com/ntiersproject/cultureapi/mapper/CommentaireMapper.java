package com.ntiersproject.cultureapi.mapper;

import com.ntiersproject.cultureapi.model.dto.Commentaire;
import com.ntiersproject.cultureapi.model.dto.Film;
import com.ntiersproject.cultureapi.repository.mysql.UtilisateurRepository;
import com.ntiersproject.cultureapi.repository.mysql.entity.CommentaireEntity;
import com.ntiersproject.cultureapi.repository.mysql.entity.FilmEntity;
import com.ntiersproject.cultureapi.repository.mysql.entity.UtilisateurEntity;

import java.util.ArrayList;
import java.util.List;

public class CommentaireMapper {

    public static Commentaire mapToDto(CommentaireEntity entity) {
        Commentaire commentaire = new Commentaire();
        commentaire.setId(entity.getId());
        commentaire.setAuteur(entity.getUtilisateur().getPseudo());
        commentaire.setContenu(entity.getTexte());
        commentaire.setDate(entity.getDate());

        return commentaire;
    }

    public static List<Commentaire> mapToDtos(List<CommentaireEntity> entities) {
        List<Commentaire> commentaires = new ArrayList<>();
        if(entities != null) {
            for(CommentaireEntity entity: entities) {
                commentaires.add(CommentaireMapper.mapToDto(entity));
            }
        }

        return commentaires;
    }

    public static CommentaireEntity mapToEntity(Commentaire commentaire, UtilisateurEntity utilisateurEntity, FilmEntity filmEntity) {
        CommentaireEntity entity = new CommentaireEntity();
        entity.setId(commentaire.getId());
        entity.setDate(commentaire.getDate());
        entity.setTexte(commentaire.getContenu());
        entity.setFilm(filmEntity);
        entity.setUtilisateur(utilisateurEntity);

        return entity;
    }

    public static List<CommentaireEntity> mapToEntities(List<Commentaire> commentaires, UtilisateurEntity utilisateurEntity, FilmEntity filmEntity) {
        List<CommentaireEntity> commentaireEntities = new ArrayList<>();
        if(commentaires != null) {
            for(Commentaire commentaire: commentaires) {
                commentaireEntities.add(mapToEntity(commentaire, utilisateurEntity, filmEntity));
            }
        }

        return commentaireEntities;
    }
}
