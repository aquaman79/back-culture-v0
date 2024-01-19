package com.ntiersproject.cultureapi.security;

import com.ntiersproject.cultureapi.bean.Role;
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

    @Inject
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UtilisateurEntity utilisateurEntity = utilisateurRepository.findByEmail(username);

        Role role = utilisateurEntity.getIsAdmin() ? Role.ADMIN : Role.USER;
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));

        return new User(utilisateurEntity.getNom(), utilisateurEntity.getMotDePasse(), authorities);
    }
}
