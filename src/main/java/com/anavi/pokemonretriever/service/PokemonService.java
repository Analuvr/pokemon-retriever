package com.anavi.pokemonretriever.service;

import com.anavi.pokemonretriever.exception.PokemonNotFoundException;
import com.anavi.pokemonretriever.model.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PokemonService {

    // Inyección del cliente HTTP
    private final RestTemplate restTemplate;

    @Autowired
    public PokemonService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    // Metodo para buscar un Pokémon por su nombre
    public Pokemon getPokemonByName(String name){

        try{
            String url = "https://pokeapi.co/api/v2/pokemon/" + name.toLowerCase();
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            String pokemonName = (String) response.get("name");
            Double height = ((Number) response.get("height")).doubleValue();

            // Sprite (imagen)
            Map sprites = (Map) response.get("sprites");
            String image = (String) sprites.get("front_default");

            // Abilities
            List<Map> abilitiesList = (List<Map>) response.get("abilities");
            List<String> abilities = new ArrayList<>();
            for (Map abilityMap : abilitiesList){
                Map ability = (Map) abilityMap.get("ability");
                abilities.add((String) ability.get("name"));
            }

            // Forms
            List<Map> formsList = (List<Map>) response.get("forms");
            List<String> forms = new ArrayList<>();
            for (Map form : formsList){
                forms.add((String) form.get("name"));
            }
            return new Pokemon(pokemonName, image, abilities, forms, height);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new PokemonNotFoundException(name);
            } else {
                throw e; // Para otros errores
            }
        }
    }

    // Metodo para devolver una lista de 100 Pokémon
    public List<String> getFirst100Pokemon() {
        String url = "https://pokeapi.co/api/v2/pokemon?limit=100";

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        List<Map<String, String>> results = (List<Map<String, String>>) response.get("results");

        List<String> pokemonNames = new ArrayList<>();

        for (Map<String, String> pokemon : results) {
            pokemonNames.add(pokemon.get("name"));
        }

        return pokemonNames;
    }
}
