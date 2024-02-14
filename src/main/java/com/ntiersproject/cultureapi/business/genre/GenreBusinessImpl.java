package com.ntiersproject.cultureapi.business.genre;

import com.ntiersproject.cultureapi.exception.FunctionalException;
import com.ntiersproject.cultureapi.mapper.GenreMapper;
import com.ntiersproject.cultureapi.model.dto.Genre;
import com.ntiersproject.cultureapi.repository.mysql.GenreRepository;
import com.ntiersproject.cultureapi.repository.mysql.entity.GenreEntity;
import com.ntiersproject.cultureapi.utils.Constantes;
import com.ntiersproject.cultureapi.utils.FormatDonneesUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreBusinessImpl implements GenreBusiness {

    private GenreRepository genreRepository;

    public GenreBusinessImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre createGenre(Genre genre) {
        FormatDonneesUtils.trimStrings(genre);
        GenreBusinessUtils.valideDonneesEnregistrement(genre);
        verifieLibelleExistePas(genre.getLibelle());

        GenreEntity entiteAcree = new GenreEntity();
        entiteAcree.setLibelle(genre.getLibelle());
        GenreEntity entiteCree = genreRepository.save(entiteAcree);
        Genre genreCree = GenreMapper.mapToDto(entiteCree);
        return genreCree;
    }

    @Override
    public List<Genre> getAll() {
        List<GenreEntity> entites = genreRepository.findAll();
        List<Genre> genres = GenreMapper.mapToDtos(entites);
        return genres;
    }

    @Override
    public List<Genre> getById(List<Long> ids) {
        List<GenreEntity> entites = genreRepository.findAllById(ids);
        return GenreMapper.mapToDtos(entites);
    }

    @Override
    public Genre getById(Long id) {
        Optional<GenreEntity> optionalGenre = genreRepository.findById(id);
        if(!optionalGenre.isPresent()) {
            throw new FunctionalException(HttpStatus.NOT_FOUND, Constantes.MESSAGE_GENRE_NON_TROUVE);
        } else {
            return GenreMapper.mapToDto(optionalGenre.get());
        }
    }

    @Override
    public Genre updateGenre(Long id, Genre genre) {
        Optional<GenreEntity> optionalGenre = genreRepository.findById(id);
        if(!optionalGenre.isPresent()) {
            throw new FunctionalException(HttpStatus.NOT_FOUND, Constantes.MESSAGE_GENRE_NON_TROUVE);
        }

        FormatDonneesUtils.trimStrings(genre);
        if(genre == null) {
            return GenreMapper.mapToDto(optionalGenre.get());
        }

        if(genre.getLibelle() != null) {
            GenreBusinessUtils.valideLibelle(genre.getLibelle());
            verifieLibelleExistePas(genre.getLibelle());

            GenreEntity entite = optionalGenre.get();
            entite.setLibelle(genre.getLibelle());

            entite = genreRepository.save(entite);

            return GenreMapper.mapToDto(entite);
        }
        return GenreMapper.mapToDto(optionalGenre.get());

    }

    @Override
    public void deleteById(Long id) {
        if(!genreRepository.existsById(id)) {
            throw new FunctionalException(HttpStatus.NOT_FOUND, Constantes.MESSAGE_GENRE_NON_TROUVE);
        }
        genreRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        genreRepository.deleteAll();
    }

    private void verifieLibelleExistePas(String libelle) {
        if(genreRepository.existsByLibelle(libelle)) {
            throw new FunctionalException(HttpStatus.CONFLICT, "Ce libellé existe déjà");
        }
    }
}
