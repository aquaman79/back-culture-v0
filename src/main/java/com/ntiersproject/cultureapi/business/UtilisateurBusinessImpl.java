package com.ntiersproject.cultureapi.business;

import com.ntiersproject.cultureapi.exception.FunctionalException;
import com.ntiersproject.cultureapi.model.dto.Utilisateur;
import com.ntiersproject.cultureapi.mapper.UtilisateurMapper;
import com.ntiersproject.cultureapi.model.dto.UtilisateurUpdateRequest;
import com.ntiersproject.cultureapi.repository.UtilisateurRepository;
import com.ntiersproject.cultureapi.repository.entity.mysql.UtilisateurEntity;
import com.ntiersproject.cultureapi.utils.Constantes;
import com.ntiersproject.cultureapi.utils.ValidationDonneesUtils;
import jakarta.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {

        // encode mot de passe
        utilisateur.setMotDePasse(
                passwordEncoder.encode(utilisateur.getMotDePasse())
        );

        // definit la date d'inscription avec la date actuelle
        utilisateur.setDateInscription(LocalDate.now());

        verifiePseudoExistePas(utilisateur.getPseudo());
        verifieEmailExistePas(utilisateur.getEmail());

        // cree l'utilisateur
        UtilisateurEntity entity = utilisateurRepository.save(
                    UtilisateurMapper.mapToEntity(utilisateur)
            );

        Utilisateur utilisateurCree = UtilisateurMapper.mapToDto(entity);

        return utilisateurCree;
    }

    @Override
    public List<Utilisateur> getAll() {
        List<UtilisateurEntity> entities = utilisateurRepository.findAll();
        return UtilisateurMapper.mapToDtos(entities);
    }

    @Override
    public List<Utilisateur> getAllByIds(List<Long> ids) {
        List<UtilisateurEntity> entities = utilisateurRepository.findAllById(ids);
        return UtilisateurMapper.mapToDtos(entities);
    }

    @Override
    public Utilisateur getById(Long id) {
        Optional<UtilisateurEntity> optionalEntity = utilisateurRepository.findById(id);
        if(!optionalEntity.isPresent()) {
            throw new FunctionalException(HttpStatus.NOT_FOUND, Constantes.MESSAGE_UTILISATEUR_NON_TROUVE);
        } else {
            return UtilisateurMapper.mapToDto(optionalEntity.get());
        }
    }

    @Override
    public Utilisateur getByUsername(String username) {
        UtilisateurEntity entity = utilisateurRepository.findByPseudoOrEmail(username, username);
        if(entity == null) {
            throw new FunctionalException(HttpStatus.NOT_FOUND, Constantes.MESSAGE_UTILISATEUR_NON_TROUVE);
        } else {
            return UtilisateurMapper.mapToDto(entity);
        }
    }

    @Override
    public Utilisateur updateUtilisateur(Long id, UtilisateurUpdateRequest utilisateurUpdateRequest) {
        Optional<UtilisateurEntity> optionalEntity = utilisateurRepository.findById(id);
        if(!optionalEntity.isPresent()) {
            throw new FunctionalException(HttpStatus.NOT_FOUND, Constantes.MESSAGE_UTILISATEUR_NON_TROUVE);
        }

        UtilisateurEntity utilisateurTrouveEntity = optionalEntity.get();
        if(utilisateurUpdateRequest == null) {
            return UtilisateurMapper.mapToDto(utilisateurTrouveEntity);
        }

        if(utilisateurUpdateRequest.getNom() != null) {
            ValidationDonneesUtils.valideNom(utilisateurUpdateRequest.getNom());
            utilisateurTrouveEntity.setNom(utilisateurUpdateRequest.getNom());
        }

        if(utilisateurUpdateRequest.getPseudo() != null) {
            ValidationDonneesUtils.validePseudo(utilisateurUpdateRequest.getPseudo());
            verifiePseudoExistePas(utilisateurUpdateRequest.getPseudo());
            utilisateurTrouveEntity.setPseudo(utilisateurUpdateRequest.getPseudo());
        }

        if(utilisateurUpdateRequest.getEmail() != null) {
            ValidationDonneesUtils.valideEmail(utilisateurUpdateRequest.getEmail());
            verifieEmailExistePas(utilisateurUpdateRequest.getEmail());
            utilisateurTrouveEntity.setEmail(utilisateurUpdateRequest.getEmail());
        }

        if(utilisateurUpdateRequest.getMotDePasse() != null) {
            ValidationDonneesUtils.valideMotsDePasse(utilisateurUpdateRequest.getMotDePasse(), utilisateurUpdateRequest.getConfirmationMotDePasse());
            if(passwordEncoder.matches(utilisateurUpdateRequest.getMotDePasse(), utilisateurTrouveEntity.getMotDePasse())) {
                throw new FunctionalException(HttpStatus.BAD_REQUEST, "Mot de passe identique à l'ancien, veuillez changer");
            } else {
                String nouveauMotDePasse = passwordEncoder.encode(utilisateurUpdateRequest.getMotDePasse());
                utilisateurTrouveEntity.setMotDePasse(nouveauMotDePasse);
            }
        }

        UtilisateurEntity utilisateurModifieEntity = utilisateurRepository.save(utilisateurTrouveEntity);

        return UtilisateurMapper.mapToDto(utilisateurModifieEntity);
    }

    private void verifiePseudoExistePas(String pseudo) {
        if(utilisateurRepository.existsByPseudo(pseudo)) {
            throw new FunctionalException(HttpStatus.CONFLICT, "Ce pseudo existe déjà");
        }
    }

    private void verifieEmailExistePas(String email) {
        if(utilisateurRepository.existsByEmail(email)) {
            throw new FunctionalException(HttpStatus.CONFLICT, "Cette adresse e-mail existe déjà");
        }

    }

}
