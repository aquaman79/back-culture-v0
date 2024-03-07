package com.ntiersproject.cultureapi.business.utilisateur;

import com.ntiersproject.cultureapi.exception.FunctionalException;
import com.ntiersproject.cultureapi.model.dto.Utilisateur;
import com.ntiersproject.cultureapi.mapper.UtilisateurMapper;
import com.ntiersproject.cultureapi.repository.mysql.UtilisateurRepository;
import com.ntiersproject.cultureapi.repository.mysql.entity.UtilisateurEntity;
import com.ntiersproject.cultureapi.utils.Constantes;
import com.ntiersproject.cultureapi.utils.FormatDonneesUtils;
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

        UtilisateurEntity entite = UtilisateurMapper.mapToEntity(utilisateur);
        entite.setId(null);
        // cree l'utilisateur
        entite = utilisateurRepository.save(entite);

        Utilisateur utilisateurCree = UtilisateurMapper.mapToDto(entite);

        return utilisateurCree;
    }

    @Override
    public List<Utilisateur> getAll() {
        List<UtilisateurEntity> entites = utilisateurRepository.findAll();
        return UtilisateurMapper.mapToDtos(entites);
    }

    @Override
    public List<Utilisateur> getAllByIds(List<Long> ids) {
        List<UtilisateurEntity> entites = utilisateurRepository.findAllById(ids);
        return UtilisateurMapper.mapToDtos(entites);
    }

    @Override
    public Utilisateur getById(Long id) {
        Optional<UtilisateurEntity> optionalEntite = utilisateurRepository.findById(id);
        if(!optionalEntite.isPresent()) {
            throw new FunctionalException(HttpStatus.NOT_FOUND, Constantes.MESSAGE_UTILISATEUR_NON_TROUVE);
        } else {
            return UtilisateurMapper.mapToDto(optionalEntite.get());
        }
    }

    @Override
    public Utilisateur getByUsername(String username) {
        UtilisateurEntity entite = utilisateurRepository.findByPseudoOrEmail(username, username);
        if(entite == null) {
            throw new FunctionalException(HttpStatus.NOT_FOUND, Constantes.MESSAGE_UTILISATEUR_NON_TROUVE);
        } else {
            return UtilisateurMapper.mapToDto(entite);
        }
    }

    @Override
    public Utilisateur updateUtilisateur(Long id, Utilisateur utilisateur) {
        Optional<UtilisateurEntity> optionalEntite = utilisateurRepository.findById(id);
        if(!optionalEntite.isPresent()) {
            throw new FunctionalException(HttpStatus.NOT_FOUND, Constantes.MESSAGE_UTILISATEUR_NON_TROUVE);
        }

        UtilisateurEntity entite = optionalEntite.get();

        UtilisateurMapper.map(entite, utilisateur);

        UtilisateurEntity utilisateurModifieEntite = utilisateurRepository.save(entite);

        return UtilisateurMapper.mapToDto(utilisateurModifieEntite);
    }

    @Override
    public void deleteById(Long id) {
        if(!utilisateurRepository.existsById(id)) {
            throw new FunctionalException(HttpStatus.NOT_FOUND, Constantes.MESSAGE_UTILISATEUR_NON_TROUVE);
        }
        utilisateurRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        utilisateurRepository.deleteAll();
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
