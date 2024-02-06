package com.ntiersproject.cultureapi.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
public class InscriptionRequest {

    private String nom;

    private String pseudo;

    private String email;

    private String motDePasse;

    private String confirmationMotDePasse;
}
