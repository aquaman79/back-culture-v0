package com.ntiersproject.cultureapi.configuration;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/api/v1")
public class JerseyConfig extends ResourceConfig {
  public JerseyConfig() {
    packages("com.ntiersproject.cultureapi.controller");
    packages("com.ntiersproject.cultureapi.filter");
    packages("com.ntiersproject.cultureapi.exception");
    packages("com.ntiersproject.cultureapi.security");
  }
}
