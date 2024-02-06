package com.ntiersproject.cultureapi.business;

import com.ntiersproject.cultureapi.model.dto.Utilisateur;

import java.util.List;

public interface UtilisateurBusiness {
    Utilisateur creeUtilisateur(Utilisateur utilisateur);

    List<Utilisateur> get();
}
