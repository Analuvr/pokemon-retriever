package com.anavi.pokemonretriever.exception;

public class PokemonNotFoundException extends RuntimeException {
    public PokemonNotFoundException(String name) {
        super("Pokemon with name:  " + name + " not found");
    }
}
