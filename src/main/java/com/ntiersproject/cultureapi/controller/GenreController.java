package com.ntiersproject.cultureapi.controller;

import com.ntiersproject.cultureapi.business.genre.GenreBusiness;
import com.ntiersproject.cultureapi.model.Role;
import com.ntiersproject.cultureapi.model.dto.Genre;
import com.ntiersproject.cultureapi.security.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Path("/genres")
public class GenreController {

    private GenreBusiness genreBusiness;

    private JwtTokenProvider jwtTokenProvider;

    @Context
    private HttpServletRequest httpServletRequest;

    public GenreController(GenreBusiness genreBusiness, JwtTokenProvider jwtTokenProvider) {
        this.genreBusiness = genreBusiness;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGenre(Genre genre) {
        jwtTokenProvider.validateRights(httpServletRequest, Role.ADMIN);
        Genre genreCree = genreBusiness.createGenre(genre);
        return Response.status(201).entity(genreCree).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Genre> genres = genreBusiness.getAll();
        return Response.status(200).entity(genres).build();
    }

    @GET
    @Path("/by-ids")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllByIds(@QueryParam("id") List<Long> ids){
        List<Genre> genres = genreBusiness.getById(ids);
        return Response.status(200).entity(genres).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        Genre genre = genreBusiness.getById(id);
        return Response.status(200).entity(genre).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGenre(@PathParam("id") Long id, Genre genre) {
        jwtTokenProvider.validateRights(httpServletRequest, Role.ADMIN);
        Genre genreModifie = genreBusiness.updateGenre(id, genre);
        return Response.status(200).entity(genreModifie).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") Long id){
        jwtTokenProvider.validateRights(httpServletRequest, Role.ADMIN);
        genreBusiness.deleteById(id);
        return Response.status(200).entity("Genre supprimé avec succès").build();
    }

    @DELETE
    @Path("/deleteAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAll(){
        jwtTokenProvider.validateRights(httpServletRequest, Role.ADMIN);
        genreBusiness.deleteAll();
        return Response.status(200).entity("Tous les genres ont été supprimés avec succès").build();
    }
}
