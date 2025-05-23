package com.anavi.pokemonretriever.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PokemonNotFoundException.class)
    public ResponseEntity<String> handlePokemonNotFound(PokemonNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExternalApiTimeoutException.class)
    public ResponseEntity<String> handleTimeout(ExternalApiTimeoutException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.GATEWAY_TIMEOUT);
    }
}
