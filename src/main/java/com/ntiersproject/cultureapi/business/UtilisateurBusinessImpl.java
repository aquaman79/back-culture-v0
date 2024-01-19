package com.ntiersproject.cultureapi.business;

import com.ntiersproject.cultureapi.bean.Utilisateur;
import com.ntiersproject.cultureapi.mapper.UtilisateurMapper;
import com.ntiersproject.cultureapi.repository.UtilisateurRepository;
import com.ntiersproject.cultureapi.repository.entity.mysql.UtilisateurEntity;
import jakarta.inject.Inject;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

        utilisateur.setMotDePasse(
                passwordEncoder.encode(utilisateur.getMotDePasse())
        );

        utilisateur.setDateInscription(LocalDate.now());

        UtilisateurEntity utilisateurEntity = utilisateurRepository.save(
                UtilisateurMapper.getEntity(utilisateur)
        );

        Utilisateur utilisateurCree = UtilisateurMapper.getDto(utilisateurEntity);

        return utilisateurCree;
    }

    @Override
    public Utilisateur findByEmail(String email) {
        UtilisateurEntity utilisateurEntity = utilisateurRepository.findByEmail(email);

        Utilisateur utilisateur = UtilisateurMapper.getDto(utilisateurEntity);

        return utilisateur;
    }

}
