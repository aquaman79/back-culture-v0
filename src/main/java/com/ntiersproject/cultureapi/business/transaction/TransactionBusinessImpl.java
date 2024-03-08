package com.ntiersproject.cultureapi.business.transaction;

import com.ntiersproject.cultureapi.exception.FunctionalException;
import com.ntiersproject.cultureapi.mapper.FilmMapper;
import com.ntiersproject.cultureapi.mapper.ImageFilmMapper;
import com.ntiersproject.cultureapi.model.dto.Film;
import com.ntiersproject.cultureapi.model.dto.Panier;
import com.ntiersproject.cultureapi.model.dto.Transaction;
import com.ntiersproject.cultureapi.repository.mongodb.ImageFilmRepository;
import com.ntiersproject.cultureapi.repository.mongodb.entity.ImageFilmEntity;
import com.ntiersproject.cultureapi.repository.mysql.FilmRepository;
import com.ntiersproject.cultureapi.repository.mysql.PanierRepository;
import com.ntiersproject.cultureapi.repository.mysql.TransactionRepository;
import com.ntiersproject.cultureapi.repository.mysql.UtilisateurRepository;
import com.ntiersproject.cultureapi.repository.mysql.entity.FilmEntity;
import com.ntiersproject.cultureapi.repository.mysql.entity.PanierEntity;
import com.ntiersproject.cultureapi.repository.mysql.entity.TransactionEntity;
import com.ntiersproject.cultureapi.repository.mysql.entity.UtilisateurEntity;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionBusinessImpl implements TransactionBusiness {

    TransactionRepository transactionRepository;

    UtilisateurRepository utilisateurRepository;

    FilmRepository filmRepository;

    ImageFilmRepository imageFilmRepository;

    PanierRepository panierRepository;

    JavaMailSender javaMailSender;

    public TransactionBusinessImpl(TransactionRepository transactionRepository, UtilisateurRepository utilisateurRepository, FilmRepository filmRepository, ImageFilmRepository imageFilmRepository, PanierRepository panierRepository, JavaMailSender javaMailSender) {
        this.transactionRepository = transactionRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.filmRepository = filmRepository;
        this.imageFilmRepository = imageFilmRepository;
        this.panierRepository = panierRepository;
        this.javaMailSender = javaMailSender;
    }

    @Override
    @Transactional
    public List<Transaction> createTransactions(Long idUtilisateur, List<Film> films) {
        Optional<UtilisateurEntity> optionalUtilisateurEntity = utilisateurRepository.findById(idUtilisateur);

        if(!optionalUtilisateurEntity.isPresent()) {
            throw new FunctionalException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé");
        }

        UtilisateurEntity utilisateurEntity = optionalUtilisateurEntity.get();
        List<Transaction> transactions = new ArrayList<>();
        for(Film film: films) {
            FilmEntity filmEntity = filmRepository.findById(film.getId()).orElseThrow(() -> new FunctionalException(HttpStatus.NOT_FOUND, "Film d'id "+film.getId() + " non trouvé"));

            if(!transactionRepository.existsByFilmAndUtilisateur(filmEntity, utilisateurEntity)) {
                TransactionEntity entite = new TransactionEntity();
                entite.setFilm(filmEntity);
                entite.setUtilisateur(utilisateurEntity);

                entite.setDate(LocalDate.now());
                entite.setPrix(0.0);

                TransactionEntity transactionCreee = transactionRepository.save(entite);
                panierRepository.deletePanierEntityByFilmAndUtilisateur(filmEntity, utilisateurEntity);

                Transaction transaction = new Transaction();
                transaction.setId(transactionCreee.getId());
                transaction.setIdUtilisateur(transactionCreee.getUtilisateur().getId());
                transaction.setIdFilm(transactionCreee.getFilm().getId());
                transactions.add(transaction);
            } else {
                throw new FunctionalException(HttpStatus.CONFLICT, "Le film d'id "+film.getId() +" a déjà été dans les transactions");
            }

        }

        // envoi mail

        String msg = "Bonjour,\nVous avez acheté les films suivants :\n";

        for(Film film: films) {
            msg += "\t- "+ film.getTitre() + " : " + film.getUrlBandeAnnonce() + "\n";
        }

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(utilisateurEntity.getEmail());
        mail.setSubject("Achat Films");
        mail.setText(msg);
        javaMailSender.send(mail);
        return transactions;
    }

    @Override
    public List<Film> getTransactions(Long idUtilisateur) {
        Optional<UtilisateurEntity> optionalUtilisateurEntity = utilisateurRepository.findById(idUtilisateur);

        if(!optionalUtilisateurEntity.isPresent()) {
            throw new FunctionalException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé");
        }

        UtilisateurEntity utilisateurEntity = optionalUtilisateurEntity.get();
        List<TransactionEntity> transactionEntities = transactionRepository.findAllByUtilisateur(utilisateurEntity);

        List<Film> films = new ArrayList<>();
        for(TransactionEntity transactionEntity: transactionEntities) {
            Film film = FilmMapper.mapToDto(transactionEntity.getFilm());
            ImageFilmEntity imageFilmEntite = imageFilmRepository.findByIdFilm(film.getId());
            film.setImageBase64(ImageFilmMapper.mapToDto(imageFilmEntite));
            films.add(film);
        }
        return films;
    }
}
