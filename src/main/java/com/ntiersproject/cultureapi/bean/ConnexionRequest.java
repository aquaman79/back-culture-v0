package com.ntiersproject.cultureapi.bean;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ConnexionRequest {
    private String email;
    private String motDePasse;
}
