package com.ntiersproject.cultureapi.controller;

import com.ntiersproject.cultureapi.business.UtilisateurBusiness;
import com.ntiersproject.cultureapi.model.Role;
import com.ntiersproject.cultureapi.model.dto.Utilisateur;
import com.ntiersproject.cultureapi.model.dto.UtilisateurUpdateRequest;
import com.ntiersproject.cultureapi.security.JwtTokenProvider;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Path("/utilisateurs")
public class UtilisateurController {

    private UtilisateurBusiness utilisateurBusiness;

    private JwtTokenProvider jwtTokenProvider;

    @Context
    private HttpServletRequest httpServletRequest;

    @Inject
    public UtilisateurController(UtilisateurBusiness utilisateurBusiness, JwtTokenProvider jwtTokenProvider) {
        this.utilisateurBusiness = utilisateurBusiness;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        jwtTokenProvider.validateRights(httpServletRequest, Role.ADMIN);
        List<Utilisateur> utilisateurs = utilisateurBusiness.getAll();
        return Response.status(200).entity(utilisateurs).build();
    }

    @GET
    @Path("/by-ids")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllByIds(@QueryParam("id") List<Long> ids){
        jwtTokenProvider.validateRights(httpServletRequest, Role.ADMIN);
        List<Utilisateur> utilisateurs = utilisateurBusiness.getAllByIds(ids);
        return Response.status(200).entity(utilisateurs).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id){
        jwtTokenProvider.validateRights(httpServletRequest, Role.ADMIN);
        return Response.status(200).entity(utilisateurBusiness.getById(id)).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, UtilisateurUpdateRequest utilisateurUpdateRequest){
        return Response.status(200).entity(utilisateurBusiness.updateUtilisateur(id, utilisateurUpdateRequest)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") Long id){
        jwtTokenProvider.validateRights(httpServletRequest, Role.ADMIN);
        utilisateurBusiness.deleteById(id);
        return Response.status(200).entity("Utilisateur supprimé avec succès").build();
    }

    @DELETE
    @Path("/deleteAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAll(){
        jwtTokenProvider.validateRights(httpServletRequest, Role.ADMIN);
        utilisateurBusiness.deleteAll();
        return Response.status(200).entity("Tous les utilisateurs ont été supprimés avec succès").build();
    }
}
