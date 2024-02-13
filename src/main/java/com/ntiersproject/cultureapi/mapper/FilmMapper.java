package com.ntiersproject.cultureapi.mapper;

import com.ntiersproject.cultureapi.model.dto.Film;
import com.ntiersproject.cultureapi.repository.mysql.entity.FilmEntity;

public class FilmMapper {
    public static Film mapToDto(FilmEntity entite) {
        if(entite == null) {
            return null;
        }
        Film dto = new Film();
        dto.setId(entite.getId());
        dto.setTitre(entite.getTitre());
        dto.setDescription(entite.getDescription());
        dto.setDateSortie(entite.getDateSortie());
        dto.setDuree(entite.getDuree());

        return dto;
    }

    public static FilmEntity mapToEntity(Film dto) {
        if(dto == null) {
            return null;
        }
        FilmEntity entite = new FilmEntity();
        entite.setId(entite.getId());
        entite.setTitre(entite.getTitre());
        entite.setDescription(entite.getDescription());
        entite.setDateSortie(entite.getDateSortie());
        entite.setDuree(entite.getDuree());

        return entite;
    }
}
