package com.ntiersproject.cultureapi.business.panier;

import com.ntiersproject.cultureapi.exception.FunctionalException;
import com.ntiersproject.cultureapi.mapper.FilmMapper;
import com.ntiersproject.cultureapi.mapper.ImageFilmMapper;
import com.ntiersproject.cultureapi.model.dto.Film;
import com.ntiersproject.cultureapi.model.dto.Panier;
import com.ntiersproject.cultureapi.repository.mongodb.ImageFilmRepository;
import com.ntiersproject.cultureapi.repository.mongodb.entity.ImageFilmEntity;
import com.ntiersproject.cultureapi.repository.mysql.FilmRepository;
import com.ntiersproject.cultureapi.repository.mysql.PanierRepository;
import com.ntiersproject.cultureapi.repository.mysql.UtilisateurRepository;
import com.ntiersproject.cultureapi.repository.mysql.entity.FilmEntity;
import com.ntiersproject.cultureapi.repository.mysql.entity.PanierEntity;
import com.ntiersproject.cultureapi.repository.mysql.entity.UtilisateurEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PanierBusinessImpl implements PanierBusiness {

    PanierRepository panierRepository;

    UtilisateurRepository utilisateurRepository;

    FilmRepository filmRepository;

    ImageFilmRepository imageFilmRepository;

    public PanierBusinessImpl(PanierRepository panierRepository, UtilisateurRepository utilisateurRepository, FilmRepository filmRepository, ImageFilmRepository imageFilmRepository) {
        this.panierRepository = panierRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.filmRepository = filmRepository;
        this.imageFilmRepository = imageFilmRepository;
    }

    @Override
    public List<Panier> createPaniers(Long idUtilisateur, List<Film> films) {
        Optional<UtilisateurEntity> optionalUtilisateurEntity = utilisateurRepository.findById(idUtilisateur);

        if(!optionalUtilisateurEntity.isPresent()) {
            throw new FunctionalException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé");
        }

        UtilisateurEntity utilisateurEntity = optionalUtilisateurEntity.get();
        List<Panier> paniers = new ArrayList<>();
        for(Film film: films) {
            FilmEntity filmEntity = filmRepository.findById(film.getId()).orElseThrow(() -> new FunctionalException(HttpStatus.NOT_FOUND, "Film d'id "+film.getId() + " non trouvé"));

            if(!panierRepository.existsByFilmAndUtilisateur(filmEntity, utilisateurEntity)) {
                PanierEntity entite = new PanierEntity();
                entite.setFilm(filmEntity);
                entite.setUtilisateur(utilisateurEntity);

                PanierEntity panierCree = panierRepository.save(entite);
                Panier panier = new Panier();
                panier.setId(panierCree.getId());
                panier.setIdUtilisateur(panierCree.getUtilisateur().getId());
                panier.setIdFilm(panierCree.getFilm().getId());
                paniers.add(panier);
            }
        }
        return paniers;
    }

    @Override
    public List<Film> getPaniers(Long idUtilisateur) {
        Optional<UtilisateurEntity> optionalUtilisateurEntity = utilisateurRepository.findById(idUtilisateur);

        if(!optionalUtilisateurEntity.isPresent()) {
            throw new FunctionalException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé");
        }

        UtilisateurEntity utilisateurEntity = optionalUtilisateurEntity.get();
        List<PanierEntity> panierEntities = panierRepository.findAllByUtilisateur(utilisateurEntity);

        List<Film> films = new ArrayList<>();
        for(PanierEntity panierEntity: panierEntities) {
            Film film = FilmMapper.mapToDto(panierEntity.getFilm());
            ImageFilmEntity imageFilmEntite = imageFilmRepository.findByIdFilm(film.getId());
            film.setImageBase64(ImageFilmMapper.mapToDto(imageFilmEntite));
            films.add(film);
        }
        return films;
    }
}
