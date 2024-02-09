package com.ntiersproject.cultureapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntiersproject.cultureapi.model.dto.Erreur;
import com.ntiersproject.cultureapi.utils.Constantes;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Gere les exceptions d'authentification produites avant d'entrer dans les couches Controller/Business/Repository.
 */
@Component
public class ApplicationEntryPointAuthenticationExceptionHandler implements Customizer<ExceptionHandlingConfigurer<HttpSecurity>> {

    @Override
    public void customize(ExceptionHandlingConfigurer<HttpSecurity> httpSecurityExceptionHandlingConfigurer) {
        httpSecurityExceptionHandlingConfigurer
                .authenticationEntryPoint(customAuthenticationEntryPoint());
    }

    private AuthenticationEntryPoint customAuthenticationEntryPoint() {
        return (request, response, authException) -> {
            sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, Constantes.MESSAGE_PROBlEME_AUTHENTIFICATION);
        };
    }

    private void sendErrorResponse(HttpServletResponse response, int statusCode, String message) throws IOException {
        response.setStatus(statusCode);
        response.setContentType("application/json");

        Erreur erreur = new Erreur();
        erreur.setCode(statusCode);
        erreur.setDescription(message);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), erreur);
    }

}
