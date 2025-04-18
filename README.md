# Pokémon Retriever API

This project is a Java web service built with **Spring Boot** that connects to the public **PokéAPI** (https://pokeapi.co/) to retrieve information about Pokémon.

## Features

- Retrieve a list of the first 100 Pokémon with:
    - Name
    - Image
    - Abilities
    - Forms
    - Height
- Search for a specific Pokémon by **full name**.
- Search for Pokémon by **partial name fragments**.
- Search terms are stored in a **MySQL database**.
- Global exception and read timeouts to the external API.

## Technologies

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Gradle

## How to run

1. Clone the repository:

```bash
git clone https://github.com/Analuvr/pokemon-retriever.git
```
2. Open with IntelliJ IDEA
3. Configure your DB credentials in ``application.properties``:
```properties
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```
5. Run the project ``PokemonRetrieverApplication.java``

## Endpoints

| Method | Endpoint                         | Description                     |
|--------|----------------------------------|---------------------------------|
| GET    | ``/api/v1/pokemon``                  | List the first 100 Pokémon      |
| GET    | ``/api/v1/pokemon/{name}``           | Get a specific Pokémon by name  |
| GET    | ``/api/v1/pokemon/search?query=xyz`` | Search Pokémon by name fragment |