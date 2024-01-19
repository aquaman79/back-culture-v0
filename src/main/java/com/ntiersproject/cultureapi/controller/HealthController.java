package com.ntiersproject.cultureapi.controller;

import com.ntiersproject.cultureapi.utils.Constantes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
@Path("/health")
public class HealthController {
    @GET
    @PreAuthorize("hasRole('"+Constantes.ROLE_USER+"')")
    public Response getHealth() {
        return Response.ok("Yeah I am fine :)").build();
    }
}
