package com.ntiersproject.cultureapi.controller;

import com.ntiersproject.cultureapi.mapper.AuthMapper;
import com.ntiersproject.cultureapi.model.Role;
import com.ntiersproject.cultureapi.model.dto.ConnexionRequest;
import com.ntiersproject.cultureapi.business.utilisateur.UtilisateurBusiness;
import com.ntiersproject.cultureapi.model.dto.Utilisateur;
import com.ntiersproject.cultureapi.security.JwtTokenProvider;
import com.ntiersproject.cultureapi.business.utilisateur.UtilisateurBusinessUtils;
import com.ntiersproject.cultureapi.utils.FormatDonneesUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
@Path("")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private UtilisateurBusiness utilisateurBusiness;

    @Context
    private HttpServletRequest httpServletRequest;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UtilisateurBusiness utilisateurBusiness) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.utilisateurBusiness = utilisateurBusiness;
    }

    @POST
    @Path("/inscription")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inscrire(Utilisateur utilisateur){

        FormatDonneesUtils.trimStrings(utilisateur);
        UtilisateurBusinessUtils.valideDonneesEnregistrement(utilisateur);

        utilisateur.setIsAdmin(false);
        Utilisateur utilisateurCree = utilisateurBusiness.createUtilisateur(
                utilisateur
        );

        return Response.status(201).entity(utilisateurCree).build();
    }

    @POST
    @Path("/inscription/admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inscrireAdmin(Utilisateur utilisateur) {
        jwtTokenProvider.validateRights(httpServletRequest, Role.ADMIN);
        FormatDonneesUtils.trimStrings(utilisateur);
        UtilisateurBusinessUtils.valideDonneesEnregistrement(utilisateur);

        utilisateur.setIsAdmin(true);
        Utilisateur utilisateurCree = utilisateurBusiness.createUtilisateur(
                utilisateur
        );

        return Response.status(201).entity(utilisateurCree).build();
    }

    @POST
    @Path("/connexion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response connecter(ConnexionRequest connexionRequest){

        UtilisateurBusinessUtils.valideDonneesConnexion(connexionRequest);

        Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
                  connexionRequest.getUsername(), connexionRequest.getMotDePasse()
          )
        );

        String jwtToken = jwtTokenProvider.generateToken(connexionRequest.getUsername(), authentication.getAuthorities());

        Utilisateur utilisateur = utilisateurBusiness.getByUsername(connexionRequest.getUsername());

        return Response.ok(AuthMapper.mapToConnexionResponse(jwtToken, utilisateur)).build();
    }
}
