package com.anavi.pokemonretriever.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping
    public String sayHello(){
        return "Hola Anita.";
    }
}
