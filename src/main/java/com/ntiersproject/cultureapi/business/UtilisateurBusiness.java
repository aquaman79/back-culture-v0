package com.ntiersproject.cultureapi.business;

import com.ntiersproject.cultureapi.bean.Utilisateur;

public interface UtilisateurBusiness {
    Utilisateur createUtilisateur(Utilisateur utilisateur);

    Utilisateur findByEmail(String email);
}
