package com.ntiersproject.cultureapi.business.panier;

import com.ntiersproject.cultureapi.model.dto.Film;
import com.ntiersproject.cultureapi.model.dto.Panier;

import java.util.List;

public interface PanierBusiness {
    List<Panier> createPaniers(Long idUtilisateur, List<Film> films);

    List<Film> getPaniers(Long idUtilisateur);
}
