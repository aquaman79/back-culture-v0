package com.ntiersproject.cultureapi.mapper;

import com.ntiersproject.cultureapi.model.dto.InscriptionRequest;
import com.ntiersproject.cultureapi.model.dto.Utilisateur;
import com.ntiersproject.cultureapi.repository.entity.mysql.UtilisateurEntity;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurMapper {
    public static UtilisateurEntity mapToEntity(Utilisateur dto) {
        UtilisateurEntity entity = new UtilisateurEntity();
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        entity.setPseudo(dto.getPseudo());
        entity.setEmail(dto.getEmail());
        entity.setMotDePasse(dto.getMotDePasse());
        entity.setDateInscription(dto.getDateInscription());
        entity.setIsAdmin(dto.getIsAdmin() == null ? false : dto.getIsAdmin());

        return entity;
    }

    public static Utilisateur mapToDto(UtilisateurEntity entity) {
        Utilisateur dto = new Utilisateur();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setPseudo(entity.getPseudo());
        dto.setEmail(entity.getEmail());
        dto.setMotDePasse(entity.getMotDePasse());
        dto.setDateInscription(entity.getDateInscription());
        dto.setIsAdmin(entity.getIsAdmin() == null ? false : entity.getIsAdmin());

        return dto;
    }

    public static List<Utilisateur> mapToDtos(List<UtilisateurEntity> entities) {
        List<Utilisateur> dtos = new ArrayList<>();
        if(entities != null) {
            for(UtilisateurEntity entity: entities) {
                dtos.add(mapToDto(entity));
            }
        }

        return dtos;
    }

    public static Utilisateur map(InscriptionRequest inscriptionRequest) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(inscriptionRequest.getNom());
        utilisateur.setPseudo(inscriptionRequest.getPseudo());
        utilisateur.setEmail(inscriptionRequest.getEmail());
        utilisateur.setMotDePasse(inscriptionRequest.getMotDePasse());
        return utilisateur;
    }
}
