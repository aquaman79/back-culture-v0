package com.ntiersproject.cultureapi.bean;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InscriptionRequest {
    private String nom;
    private String email;
    private String motDePasse;
    private Boolean isAdmin;
}
