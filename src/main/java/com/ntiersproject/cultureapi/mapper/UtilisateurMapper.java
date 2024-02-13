package com.ntiersproject.cultureapi.mapper;

import com.ntiersproject.cultureapi.model.dto.Utilisateur;
import com.ntiersproject.cultureapi.repository.mysql.entity.UtilisateurEntity;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurMapper {
    public static UtilisateurEntity mapToEntity(Utilisateur dto) {
        if(dto == null) {
            return null;
        }
        UtilisateurEntity entite = new UtilisateurEntity();
        entite.setId(dto.getId());
        entite.setNom(dto.getNom());
        entite.setPseudo(dto.getPseudo());
        entite.setEmail(dto.getEmail());
        entite.setMotDePasse(dto.getMotDePasse());
        entite.setDateInscription(dto.getDateInscription());
        entite.setIsAdmin(dto.getIsAdmin() == null ? false : dto.getIsAdmin());

        return entite;
    }

    public static Utilisateur mapToDto(UtilisateurEntity entite) {
        if(entite == null) {
            return null;
        }
        Utilisateur dto = new Utilisateur();
        dto.setId(entite.getId());
        dto.setNom(entite.getNom());
        dto.setPseudo(entite.getPseudo());
        dto.setEmail(entite.getEmail());
        dto.setMotDePasse(entite.getMotDePasse());
        dto.setDateInscription(entite.getDateInscription());
        dto.setIsAdmin(entite.getIsAdmin() == null ? false : entite.getIsAdmin());

        return dto;
    }

    public static List<Utilisateur> mapToDtos(List<UtilisateurEntity> entites) {

        List<Utilisateur> dtos = new ArrayList<>();
        if(entites != null) {
            for(UtilisateurEntity entite: entites) {
                dtos.add(mapToDto(entite));
            }
        }

        return dtos;
    }
}
