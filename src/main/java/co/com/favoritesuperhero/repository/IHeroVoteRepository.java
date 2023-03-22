package co.com.favoritesuperhero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.favoritesuperhero.entities.HeroVote;

public interface IHeroVoteRepository extends JpaRepository<HeroVote, Integer> {

	@Query("SELECT idHero, COUNT(*) FROM HeroVote GROUP BY idHero")
	List<Object[]> countByIdHero();

}
