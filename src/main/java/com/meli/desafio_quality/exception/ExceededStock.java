package com.meli.desafio_quality.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ExceededStock extends RuntimeException {
    public ExceededStock(String message) {
        super(message);
    }
}
