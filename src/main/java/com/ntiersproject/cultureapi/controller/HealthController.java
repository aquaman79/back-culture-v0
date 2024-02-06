package com.ntiersproject.cultureapi.controller;

import com.ntiersproject.cultureapi.utils.Constantes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasRole;

@Controller
@Path("/health")
public class HealthController {
    @GET
    public Response getHealth() {
        return Response.ok("Yeah I am fine :)").build();
    }
}
