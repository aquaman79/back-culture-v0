package com.ntiersproject.cultureapi.mapper;

import com.ntiersproject.cultureapi.bean.InscriptionRequest;
import com.ntiersproject.cultureapi.bean.Utilisateur;
import com.ntiersproject.cultureapi.repository.entity.mysql.UtilisateurEntity;

public class UtilisateurMapper {
    public static UtilisateurEntity getEntity(Utilisateur utilisateur) {
        UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        utilisateurEntity.setId(utilisateur.getId());
        utilisateurEntity.setNom(utilisateur.getNom());
        utilisateurEntity.setEmail(utilisateur.getEmail());
        utilisateurEntity.setMotDePasse(utilisateur.getMotDePasse());
        utilisateurEntity.setDateInscription(utilisateur.getDateInscription());
        utilisateurEntity.setIsAdmin(utilisateur.getIsAdmin());

        return utilisateurEntity;
    }

    public static Utilisateur getDto(UtilisateurEntity utilisateurEntity) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurEntity.getId());
        utilisateur.setNom(utilisateurEntity.getNom());
        utilisateur.setEmail(utilisateurEntity.getEmail());
        utilisateur.setMotDePasse(utilisateurEntity.getMotDePasse());
        utilisateur.setDateInscription(utilisateurEntity.getDateInscription());
        utilisateur.setIsAdmin(utilisateurEntity.getIsAdmin());

        return utilisateur;
    }

    public static Utilisateur mapDto(InscriptionRequest inscriptionRequest) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(inscriptionRequest.getNom());
        utilisateur.setEmail(inscriptionRequest.getEmail());
        utilisateur.setMotDePasse(inscriptionRequest.getMotDePasse());
        utilisateur.setIsAdmin(inscriptionRequest.getIsAdmin());

        return utilisateur;
    }
}
