package co.com.favoritesuperhero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.favoritesuperhero.entities.Suggestion;

public interface ISuggestionRepository extends JpaRepository<Suggestion, Integer> {

}
