package com.ntiersproject.cultureapi.controller;

import com.ntiersproject.cultureapi.bean.ConnexionRequest;
import com.ntiersproject.cultureapi.bean.ConnexionResponse;
import com.ntiersproject.cultureapi.bean.InscriptionRequest;
import com.ntiersproject.cultureapi.bean.Utilisateur;
import com.ntiersproject.cultureapi.business.UtilisateurBusiness;
import com.ntiersproject.cultureapi.filter.JwtTokenProvider;
import com.ntiersproject.cultureapi.mapper.UtilisateurMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Path("")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private PasswordEncoder passwordEncoder;

    private UtilisateurBusiness utilisateurBusiness;

    @Inject
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder, UtilisateurBusiness utilisateurBusiness) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.utilisateurBusiness = utilisateurBusiness;
    }

    @POST
    @Path("/inscription")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inscrire(InscriptionRequest inscriptionRequest){

        utilisateurBusiness.createUtilisateur(
                UtilisateurMapper.mapDto(inscriptionRequest)
        );

        return Response.status(201).build();
    }

    @POST
    @Path("/connexion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response connecter(ConnexionRequest connexionRequest){

        authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
                  connexionRequest.getEmail(),connexionRequest.getMotDePasse()
          )
        );
        String jwtToken = jwtTokenProvider.generateToken(connexionRequest.getEmail());

        ConnexionResponse connexionResponse = new ConnexionResponse();
        connexionResponse.setAccessToken(jwtToken);

        return Response.ok(connexionResponse).build();
    }
}
