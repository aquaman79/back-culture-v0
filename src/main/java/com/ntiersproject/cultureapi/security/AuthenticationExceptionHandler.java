package com.ntiersproject.cultureapi.security;

import com.ntiersproject.cultureapi.model.dto.Erreur;
import com.ntiersproject.cultureapi.utils.Constantes;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.springframework.security.core.AuthenticationException;

/**
 * Gere les exceptions d'authentification produites au minimum dans la couche Controller, et pas avant.
 */
@Provider
public class AuthenticationExceptionHandler implements ExceptionMapper<AuthenticationException> {
    @Override
    public Response toResponse(AuthenticationException exception) {
        Erreur erreur = new Erreur();
        erreur.setCode(401);
        erreur.setDescription(Constantes.MESSAGE_PROBlEME_AUTHENTIFICATION);

        return Response.status(erreur.getCode()).entity(erreur).build();
    }
}
