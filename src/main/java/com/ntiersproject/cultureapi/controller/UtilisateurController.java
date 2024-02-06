package com.ntiersproject.cultureapi.controller;

import com.ntiersproject.cultureapi.business.UtilisateurBusiness;
import com.ntiersproject.cultureapi.model.dto.InscriptionRequest;
import com.ntiersproject.cultureapi.model.dto.Utilisateur;
import com.ntiersproject.cultureapi.utils.Constantes;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Path("/utilisateurs")
public class UtilisateurController {

    private UtilisateurBusiness utilisateurBusiness;

    @Inject
    public UtilisateurController(UtilisateurBusiness utilisateurBusiness) {
        this.utilisateurBusiness = utilisateurBusiness;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(){
        List<Utilisateur> utilisateurs = utilisateurBusiness.get();
        return Response.status(200).entity(utilisateurs).build();
    }
}
