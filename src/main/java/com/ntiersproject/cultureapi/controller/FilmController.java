package com.ntiersproject.cultureapi.controller;

import com.ntiersproject.cultureapi.business.film.FilmBusiness;
import com.ntiersproject.cultureapi.model.Role;
import com.ntiersproject.cultureapi.model.dto.Film;
import com.ntiersproject.cultureapi.security.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Path("/films")
public class FilmController {
    private FilmBusiness filmBusiness;

    private JwtTokenProvider jwtTokenProvider;

    @Context
    private HttpServletRequest httpServletRequest;

    public FilmController(FilmBusiness filmBusiness, JwtTokenProvider jwtTokenProvider) {
        this.filmBusiness = filmBusiness;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createFilm(Film film) {
        jwtTokenProvider.validateRights(httpServletRequest, Role.ADMIN);
        Film filmCree = filmBusiness.createFilm(film);
        return Response.status(201).entity(filmCree).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        List<Film> films = filmBusiness.getAll();

        return Response.status(200).entity(films).build();
    }

    @GET
    @Path("/by-ids")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllByIds(@QueryParam("id") List<Long> ids){
        List<Film> films = filmBusiness.getAllByIds(ids);

        return Response.status(200).entity(films).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        Film film = filmBusiness.getById(id);
        return Response.status(200).entity(film).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFilm(@PathParam("id") Long id, Film film) {
        Film filmModifie = filmBusiness.updateFilm(id, film);
        return Response.status(200).entity(filmModifie).build();
    }
}
