package com.anavi.pokemonretriever.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

        // Timeout de conexión
        factory.setConnectTimeout(5000); // 5 segundos

        // Timeout de lectura
        factory.setReadTimeout(5000);

        return new RestTemplate(factory);
    }
}
