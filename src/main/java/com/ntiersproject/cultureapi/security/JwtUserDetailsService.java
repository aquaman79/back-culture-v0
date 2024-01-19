package com.ntiersproject.cultureapi.security;

import com.ntiersproject.cultureapi.repository.UtilisateurRepository;
import com.ntiersproject.cultureapi.repository.entity.mysql.UtilisateurEntity;
import jakarta.inject.Inject;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    @Inject
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UtilisateurEntity utilisateurEntity = utilisateurRepository.findByEmail(username);

        return new User(utilisateurEntity.getNom(), utilisateurEntity.getMotDePasse(), new ArrayList<>());
    }
}
