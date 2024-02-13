package com.ntiersproject.cultureapi.business.utilisateur;

import com.ntiersproject.cultureapi.model.dto.Utilisateur;

import java.util.List;

public interface UtilisateurBusiness {
    Utilisateur createUtilisateur(Utilisateur utilisateur);

    List<Utilisateur> getAll();

    List<Utilisateur> getAllByIds(List<Long> ids);

    Utilisateur getById(Long id);

    /**
     *
     * @param username pseudo ou email
     * @return utilisateur
     */
    Utilisateur getByUsername(String username);

    /**
     * Modifie les informations non null de la dto Utilisateur
     *
     * @param id
     * @param utilisateur
     * @return l'utilisateur
     */
    Utilisateur updateUtilisateur(Long id, Utilisateur utilisateur);

    void deleteById(Long id);

    void deleteAll();
}
