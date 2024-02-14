package com.ntiersproject.cultureapi.mapper;

import com.ntiersproject.cultureapi.model.dto.Film;
import com.ntiersproject.cultureapi.repository.mysql.entity.FilmEntity;

import java.util.ArrayList;
import java.util.List;

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

        List<Long> idsGenres = new ArrayList<>();
        if(entite.getGenres()!=null){
            idsGenres = entite.getGenres().stream().map(i -> i.getId()).toList();
        }

        dto.setIdsGenres(idsGenres);

        return dto;
    }

    public static List<Film> mapToDtos(List<FilmEntity> entites) {
        List<Film> dtos = new ArrayList<>();
        if(entites != null) {
            for (FilmEntity entite: entites) {
                dtos.add(mapToDto(entite));
            }
        }

        return dtos;
    }

    public static FilmEntity mapToEntity(Film dto) {
        if(dto == null) {
            return null;
        }
        FilmEntity entite = new FilmEntity();
        entite.setId(dto.getId());
        entite.setTitre(dto.getTitre());
        entite.setDescription(dto.getDescription());
        entite.setDateSortie(dto.getDateSortie());
        entite.setDuree(dto.getDuree());

        return entite;
    }
}
