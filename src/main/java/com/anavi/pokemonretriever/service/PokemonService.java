package com.anavi.pokemonretriever.service;

import com.anavi.pokemonretriever.PokemonRetrieverApplication;
import com.anavi.pokemonretriever.exception.ExternalApiTimeoutException;
import com.anavi.pokemonretriever.exception.PokemonNotFoundException;
import com.anavi.pokemonretriever.model.Pokemon;
import com.anavi.pokemonretriever.model.SearchHistory;
import com.anavi.pokemonretriever.repository.SearchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PokemonService {

    // Inyección del cliente HTTP
    private final RestTemplate restTemplate;
    private final SearchHistoryRepository historyRepository;

    @Autowired
    public PokemonService(RestTemplate restTemplate, SearchHistoryRepository historyRepository){
        this.restTemplate = restTemplate;
        this.historyRepository = historyRepository;
    }

    // Metodo para buscar un Pokémon por su nombre
    public Pokemon getPokemonByName(String name){
        try {
            String url = "https://pokeapi.co/api/v2/pokemon/" + name.toLowerCase();
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            String pokemonName = (String) response.get("name");

            // Sprite (imagen)
            Map<String, String> sprites = (Map<String, String>) response.get("sprites");
            String image = sprites.get("front_default");

            // Abilities
            List<Map<String, Object>> abilitiesList = (List<Map<String, Object>>) response.get("abilities");
            List<String> abilities = new ArrayList<>();
            for (Map<String, Object> abilityMap : abilitiesList){
                Map<String, String> ability = (Map<String, String>) abilityMap.get("ability");
                abilities.add(ability.get("name"));
            }

            // Forms
            List<Map<String, String>> formsList = (List<Map<String, String>>) response.get("forms");
            List<String> forms = new ArrayList<>();
            for (Map<String, String> form : formsList){
                forms.add(form.get("name"));
            }

            Double height = ((Number) response.get("height")).doubleValue();

            return new Pokemon(pokemonName, image, abilities, forms, height);

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new PokemonNotFoundException(name);
            } else {
                throw e; // Para otros errores
            }
        } catch (ResourceAccessException e) {
            throw new ExternalApiTimeoutException();
        }
    }

    // Metodo para devolver una lista de 100 Pokémon
    public List<Pokemon> getFirst100Pokemon() {
        try {
            String url = "https://pokeapi.co/api/v2/pokemon?limit=100";

            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            List<Map<String, String>> results = (List<Map<String, String>>) response.get("results");

            List<Pokemon> pokemonList = new ArrayList<>();

            for (Map<String, String> result : results) {
                String name = result.get("name");
                Pokemon pokemon = getPokemonByName(name);
                pokemonList.add(pokemon);
            }

            return pokemonList;
        } catch (ResourceAccessException e) {
            throw new ExternalApiTimeoutException();
        }
    }

    // Metodo para filtrar nombres por fragmento de texto
    public List<String> searchPokemonByString(String fragment) {
        try {
            // Guarda la búsqueda
            SearchHistory history = new SearchHistory(fragment.toLowerCase(), LocalDateTime.now());
            historyRepository.save(history);

            String url = "https://pokeapi.co/api/v2/pokemon?limit=1302";

            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            List<Map<String, String>> results = (List<Map<String, String>>) response.get("results");

            List<String> matchingNames = new ArrayList<>();

            for (Map<String, String> result : results) {
                String name = result.get("name");
                if (name.contains(fragment.toLowerCase())){
                    matchingNames.add(name);
                }
            }

            return matchingNames;
        } catch (ResourceAccessException e) {
            throw new ExternalApiTimeoutException();
        }
    }
}
