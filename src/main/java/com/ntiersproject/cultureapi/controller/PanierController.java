package com.ntiersproject.cultureapi.controller;

import com.ntiersproject.cultureapi.business.film.FilmBusiness;
import com.ntiersproject.cultureapi.business.panier.PanierBusiness;
import com.ntiersproject.cultureapi.model.Role;
import com.ntiersproject.cultureapi.model.dto.Film;
import com.ntiersproject.cultureapi.model.dto.Panier;
import com.ntiersproject.cultureapi.security.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Path("/paniers")
public class PanierController {

    private PanierBusiness panierBusiness;

    private JwtTokenProvider jwtTokenProvider;

    @Context
    private HttpServletRequest httpServletRequest;

    public PanierController(PanierBusiness panierBusiness, JwtTokenProvider jwtTokenProvider) {
        this.panierBusiness = panierBusiness;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @POST
    @Path("/{idUtilisateur}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPaniers(@PathParam("idUtilisateur") Long idUtilisateur, List<Film> films) {
        List<Panier> paniers = panierBusiness.createPaniers(idUtilisateur, films);
        return Response.status(201).entity(paniers).build();
    }

    @GET
    @Path("/{idUtilisateur}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPaniers(@PathParam("idUtilisateur") Long idUtilisateur) {
        List<Film> films = panierBusiness.getPaniers(idUtilisateur);
        return Response.status(200).entity(films).build();
    }
}
