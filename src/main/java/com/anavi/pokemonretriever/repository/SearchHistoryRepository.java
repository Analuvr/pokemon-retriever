package com.anavi.pokemonretriever.repository;

import com.anavi.pokemonretriever.model.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {
}
