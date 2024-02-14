package com.ntiersproject.cultureapi.mapper;

import com.ntiersproject.cultureapi.model.dto.VideoFilm;
import com.ntiersproject.cultureapi.repository.mongodb.entity.VideoFilmEntity;

import java.util.Base64;

public class VideoFilmMapper {
    public static VideoFilm mapToDto(VideoFilmEntity entite) {
        if(entite == null) {
            return null;
        }

        VideoFilm dto = new VideoFilm();
        dto.setIsBandeAnnonce(entite.getIsBandeAnnonce());
        dto.setIdFilm(entite.getIdFilm());
        dto.setNom(entite.getNom());
        dto.setDataBase64(Base64.getEncoder().encodeToString(entite.getBytes()));

        return dto;
    }

    public static VideoFilmEntity mapToEntity(VideoFilm dto) {
        if(dto == null) {
            return null;
        }

        VideoFilmEntity entite = new VideoFilmEntity();
        entite.setIsBandeAnnonce(dto.getIsBandeAnnonce());
        entite.setIdFilm(dto.getIdFilm());
        entite.setNom(dto.getNom());
        entite.setBytes(Base64.getDecoder().decode(dto.getDataBase64()));

        return entite;
    }
}
