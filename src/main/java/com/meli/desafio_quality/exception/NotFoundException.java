package com.meli.desafio_quality.exception;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

    private final HttpStatus status;

    public NotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
