package com.ntiersproject.cultureapi.exception;

import com.ntiersproject.cultureapi.model.dto.Erreur;
import com.ntiersproject.cultureapi.utils.Constantes;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.glassfish.jersey.server.ParamException;
import org.springframework.http.HttpStatus;

@Provider
public class PathParamExceptionHandler implements ExceptionMapper<ParamException.PathParamException> {

    @Override
    public Response toResponse(ParamException.PathParamException exception) {
        Erreur erreur = new Erreur();
        erreur.setCode(HttpStatus.BAD_REQUEST.value());
        erreur.setDescription(Constantes.MESSAGE_MAUVAIS_FORMAT_REQUETE);

        return Response.status(HttpStatus.BAD_REQUEST.value()).entity(erreur).build();
    }
}
