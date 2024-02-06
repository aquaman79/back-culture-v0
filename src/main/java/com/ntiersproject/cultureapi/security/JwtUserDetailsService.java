package com.ntiersproject.cultureapi.security;

import com.ntiersproject.cultureapi.model.Role;
import com.ntiersproject.cultureapi.repository.UtilisateurRepository;
import com.ntiersproject.cultureapi.repository.entity.mysql.UtilisateurEntity;
import jakarta.inject.Inject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    private UtilisateurRepository utilisateurRepository;

    @Inject
    public JwtUserDetailsService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UtilisateurEntity utilisateurEntity = utilisateurRepository.findByPseudoOrEmail(username, username);

        Role role = utilisateurEntity.getIsAdmin() ? Role.ADMIN : Role.USER;
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));

        String usernamePseudoOuEmail;
        if(utilisateurEntity.getPseudo().equals(username)) {
            usernamePseudoOuEmail = utilisateurEntity.getPseudo();
        } else {
            usernamePseudoOuEmail = utilisateurEntity.getEmail();
        }
        return new User(usernamePseudoOuEmail, utilisateurEntity.getMotDePasse(), authorities);
    }
}
