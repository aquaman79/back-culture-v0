package com.ntiersproject.cultureapi.filter;

import com.ntiersproject.cultureapi.model.dto.Erreur;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;


/**
 * Gere les retours suite aux erreurs de validation des donnees d'entree des services
 */
public class ConstraintViolationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        Erreur erreur = new Erreur();
        erreur.setCode(Response.Status.BAD_REQUEST.getStatusCode());
        erreur.setDescription("Mauvais format des donnees d'entree");

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(erreur)
                .build();
    }
}
