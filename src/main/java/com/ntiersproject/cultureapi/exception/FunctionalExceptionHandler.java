package com.ntiersproject.cultureapi.exception;

import com.ntiersproject.cultureapi.model.dto.Erreur;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class FunctionalExceptionHandler implements ExceptionMapper<FunctionalException> {
    @Override
    public Response toResponse(FunctionalException exception) {
        Erreur erreur = new Erreur();
        erreur.setCode(exception.getStatut().value());
        erreur.setDescription(exception.getMessage());

        return Response.status(exception.getStatut().value()).entity(erreur).build();
    }
}
