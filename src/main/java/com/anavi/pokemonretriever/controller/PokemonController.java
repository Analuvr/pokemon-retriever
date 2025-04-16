package com.anavi.pokemonretriever.controller;

import com.anavi.pokemonretriever.model.Pokemon;
import com.anavi.pokemonretriever.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/pokemon")
public class PokemonController {
    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public List<Pokemon> getFirst100Pokemon() {
        return pokemonService.getFirst100Pokemon();
    }

    @GetMapping("/{name}")
    public Pokemon getPokemonByName(@PathVariable String name) {
        return pokemonService.getPokemonByName(name);
    }

    @GetMapping("/search")
    public List<String> searchPokemon(@RequestParam String query) {
        return pokemonService.searchPokemonByString(query);
    }

}
