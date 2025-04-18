package com.anavi.pokemonretriever.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "history")
public class SearchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pokemon")
    private Long id;

    @Column(length = 50, nullable = false)
    private String query;

    @Column(name = "search_date")
    private LocalDateTime searchAt;

    public SearchHistory() {
    }

    public SearchHistory(String query, LocalDateTime searchAt) {
        this.id = id;
        this.query = query;
        this.searchAt = searchAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public LocalDateTime getSearchAt() {
        return searchAt;
    }

    public void setSearchAt(LocalDateTime searchAt) {
        this.searchAt = searchAt;
    }
}
