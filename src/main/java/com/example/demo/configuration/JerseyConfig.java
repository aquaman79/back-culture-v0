package com.example.demo.configuration;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/api/v1")
public class JerseyConfig extends ResourceConfig {
  public JerseyConfig() {
    packages("com/example/demo/controller");
//    packages("com.m2.tiila.weather.filter");
//    packages("com.m2.tiila.weather.exception");
  }
}
