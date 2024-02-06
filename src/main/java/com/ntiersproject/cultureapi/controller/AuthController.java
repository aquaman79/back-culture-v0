package com.ntiersproject.cultureapi.controller;

import com.ntiersproject.cultureapi.model.dto.ConnexionRequest;
import com.ntiersproject.cultureapi.model.dto.ConnexionResponse;
import com.ntiersproject.cultureapi.model.dto.InscriptionRequest;
import com.ntiersproject.cultureapi.business.UtilisateurBusiness;
import com.ntiersproject.cultureapi.security.JwtTokenProvider;
import com.ntiersproject.cultureapi.mapper.UtilisateurMapper;
import com.ntiersproject.cultureapi.utils.ValidationDonneesUtils;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;

@Controller
@Path("")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private UtilisateurBusiness utilisateurBusiness;

    @Inject
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UtilisateurBusiness utilisateurBusiness) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.utilisateurBusiness = utilisateurBusiness;
    }

    @POST
    @Path("/inscription")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inscrire(InscriptionRequest inscriptionRequest){

        ValidationDonneesUtils.valideDonneesInscription(inscriptionRequest);

        utilisateurBusiness.creeUtilisateur(
                UtilisateurMapper.map(inscriptionRequest)
        );

        return Response.status(201).build();
    }

    @POST
    @Path("/connexion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response connecter(ConnexionRequest connexionRequest){

        ValidationDonneesUtils.valideDonneesConnexion(connexionRequest);

        authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
                  connexionRequest.getUsername(), connexionRequest.getMotDePasse()
          )
        );
        String jwtToken = jwtTokenProvider.generateToken(connexionRequest.getUsername());

        ConnexionResponse connexionResponse = new ConnexionResponse();
        connexionResponse.setJetonJWT(jwtToken);

        return Response.ok(connexionResponse).build();
    }
}
