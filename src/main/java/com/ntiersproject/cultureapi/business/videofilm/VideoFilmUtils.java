package com.ntiersproject.cultureapi.business.videofilm;

import com.ntiersproject.cultureapi.exception.FunctionalException;
import com.ntiersproject.cultureapi.model.dto.VideoFilm;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public class VideoFilmUtils {
    public static void valideDonneesEnregistrement(VideoFilm videoFilm) {
        if(videoFilm == null) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "La vidéo doit être fournie");
        }

        if(!StringUtils.hasText(videoFilm.getNom())) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "Le nom doit être renseigné");
        }

        if(!StringUtils.hasText(videoFilm.getDataBase64())) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "La vidéo en base64 doit être fournie");
        }

    }

    public static void valideDonneesEnregistrementVideoBandeAnnonce(VideoFilm videoFilm) {
        if(videoFilm == null) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "La vidéo bande d'annonce doit être fournie");
        }

        if(!StringUtils.hasText(videoFilm.getNom())) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "Le nom doit être renseigné");
        }

        if(!StringUtils.hasText(videoFilm.getDataBase64())) {
            throw new FunctionalException(HttpStatus.BAD_REQUEST, "La vidéo bande d'annonce en base64 doit être fournie");
        }

    }
}
