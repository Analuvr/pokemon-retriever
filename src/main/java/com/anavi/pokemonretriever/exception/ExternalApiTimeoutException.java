package com.anavi.pokemonretriever.exception;

public class ExternalApiTimeoutException extends RuntimeException {
    public ExternalApiTimeoutException() {
        super("Pokémon API took too long to respond. Please try again later.");
    }
}
