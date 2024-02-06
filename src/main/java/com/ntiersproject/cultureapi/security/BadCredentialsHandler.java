package com.ntiersproject.cultureapi.security;

import com.ntiersproject.cultureapi.model.dto.Erreur;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.springframework.security.authentication.BadCredentialsException;

@Provider
public class BadCredentialsHandler implements ExceptionMapper<BadCredentialsException> {
    @Override
    public Response toResponse(BadCredentialsException exception) {
        Erreur erreur = new Erreur();
        erreur.setCode(401);
        erreur.setDescription("Informations de connexion incorrectes");

        return Response.status(erreur.getCode()).entity(erreur).build();
    }
}
