package com.ntiersproject.cultureapi.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class FunctionalException extends RuntimeException {
    private HttpStatus statut;

    public FunctionalException(HttpStatus statut, String message) {
        super(message);
        this.statut = statut;
    }
}
