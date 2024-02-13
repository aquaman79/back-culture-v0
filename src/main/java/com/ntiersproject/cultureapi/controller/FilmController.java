package com.ntiersproject.cultureapi.controller;

import com.ntiersproject.cultureapi.business.film.FilmBusiness;
import com.ntiersproject.cultureapi.model.Role;
import com.ntiersproject.cultureapi.model.dto.Film;
import com.ntiersproject.cultureapi.model.dto.Genre;
import com.ntiersproject.cultureapi.security.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Controller;

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
        return null;
    }
}
