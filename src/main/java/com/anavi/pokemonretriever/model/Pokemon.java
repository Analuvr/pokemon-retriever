package com.anavi.pokemonretriever.model;

import java.util.List;
import java.util.Objects;

public class Pokemon {
    private String name;
    private String image;
    private List<String> abilities;
    private List<String> forms;
    private double height;

    public Pokemon() {
    }

    public Pokemon(String name, String image, List<String> abilities, List<String> forms, double height) {
        this.name = name;
        this.image = image;
        this.abilities = abilities;
        this.forms = forms;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    public List<String> getForms() {
        return forms;
    }

    public void setForms(List<String> forms) {
        this.forms = forms;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", abilities=" + abilities +
                ", forms=" + forms +
                ", height=" + height +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return Double.compare(height, pokemon.height) == 0 && Objects.equals(name, pokemon.name) && Objects.equals(image, pokemon.image) && Objects.equals(abilities, pokemon.abilities) && Objects.equals(forms, pokemon.forms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, image, abilities, forms, height);
    }
}
