package com.ntiersproject.cultureapi.business;

import com.ntiersproject.cultureapi.exception.FunctionalException;
import com.ntiersproject.cultureapi.model.dto.Utilisateur;
import com.ntiersproject.cultureapi.mapper.UtilisateurMapper;
import com.ntiersproject.cultureapi.repository.UtilisateurRepository;
import com.ntiersproject.cultureapi.repository.entity.mysql.UtilisateurEntity;
import jakarta.inject.Inject;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

@Service
public class UtilisateurBusinessImpl implements UtilisateurBusiness {

    private UtilisateurRepository utilisateurRepository;

    private PasswordEncoder passwordEncoder;

    @Inject
    public UtilisateurBusinessImpl(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Utilisateur creeUtilisateur(Utilisateur utilisateur) {

        // encode mot de passe
        utilisateur.setMotDePasse(
                passwordEncoder.encode(utilisateur.getMotDePasse())
        );

        // definit la date d'inscription avec la date actuelle
        utilisateur.setDateInscription(LocalDate.now());

        // Verifie si le pseudo existe deja
        if(utilisateurRepository.existsByPseudo(utilisateur.getPseudo())) {
            throw new FunctionalException(HttpStatus.CONFLICT, "Ce pseudo existe déjà");
        }

        // Verifie si l'adresse e-mail existe deja
        if(utilisateurRepository.existsByEmail(utilisateur.getEmail())) {
            throw new FunctionalException(HttpStatus.CONFLICT, "Cette adresse e-mail existe déjà");
        }

        // cree l'utilisateur
        UtilisateurEntity entity = utilisateurRepository.save(
                    UtilisateurMapper.mapToEntity(utilisateur)
            );

        Utilisateur utilisateurCree = UtilisateurMapper.mapToDto(entity);

        return utilisateurCree;
    }

    @Override
    public List<Utilisateur> get() {
        List<UtilisateurEntity> entities = utilisateurRepository.findAll();
        return UtilisateurMapper.mapToDtos(entities);
    }

}
