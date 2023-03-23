package co.com.favoritesuperhero.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.favoritesuperhero.client.IHeroApiClient;
import co.com.favoritesuperhero.entities.Suggestion;
import co.com.favoritesuperhero.entities.HeroVote;
import co.com.favoritesuperhero.repository.ISuggestionRepository;
import co.com.favoritesuperhero.repository.IHeroVoteRepository;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/hero")
@AllArgsConstructor
@CrossOrigin(origins = {"https://herovotegui.web.app"})
public class ApiMainController {

	private IHeroApiClient heroClient;

	private IHeroVoteRepository voteRepository;
	
	private ISuggestionRepository suggestionRepository;

	@GetMapping("/list")
	public List<Map<String, Object>> listAllHeroes() {
		return heroClient.findAllHeroes();
	}

	@GetMapping("/{alias}")
	public Map<String, Object> findHero(@PathVariable String alias) {
		Map<String, Object> hero = heroClient.findAllHeroes().stream()
				.filter(p -> p.get("alias").equals(alias.toLowerCase())).findFirst().get();
		return hero;
	}

	// find hero by alias and save vote for this
	@PostMapping("/vote/{alias}")
	public HeroVote heroVote(@PathVariable String alias, @RequestBody HeroVote heroVote) {
		Map<String, Object> hero = heroClient.findAllHeroes().stream()
				.filter(p -> p.get("alias").equals(alias.toLowerCase())).findFirst().get();

		heroVote.setIdHero((Integer) hero.get("id"));

		return voteRepository.save(heroVote);
	}

	@GetMapping("/count-votes")
	public List<Map<String, Object>> countComments() {

		// hero complete info (id_hero, alias, ect ...)
		List<Map<String, Object>> heroInfo = heroClient.findAllHeroes();

		// hero vote data (id_hero, votos)
		List<Object[]> heroVotes = voteRepository.countByIdHero();

		// method response (id_hero, alias, total_votes)
		List<Map<String, Object>> heroInfoVoteDataList = new ArrayList<>();

		for (Map<String, Object> hero : heroInfo) {
			boolean heroHasVote = false;

			for (Object[] voto : heroVotes) {
				if (voto[0].equals(hero.get("id"))) {
					Map<String, Object> heroInfoVoteData = new HashMap<>();
					heroInfoVoteData.put("idHero", voto[0]);
					heroInfoVoteData.put("alias", hero.get("alias"));
					heroInfoVoteData.put("votes", voto[1]);
					heroInfoVoteDataList.add(heroInfoVoteData);
					heroHasVote = true;
					break;
				}
			}

			if (!heroHasVote) {
				Map<String, Object> heroInfoVoteData = new HashMap<>();
				heroInfoVoteData.put("idHero", hero.get("id"));
				heroInfoVoteData.put("alias", hero.get("alias"));
				heroInfoVoteData.put("votes", 0);
				heroInfoVoteDataList.add(heroInfoVoteData);
			}
		}

		return heroInfoVoteDataList;
	}

	@GetMapping("/comments/{idHero}")
	public List<Map<String, Object>> findByIdHero(@PathVariable Integer idHero) {
		List<Object[]> allComments = voteRepository.findCommentsByIdHero(idHero);
		List<Map<String, Object>> commentByHero = new ArrayList<>();

		for (Object[] comment : allComments) {
			if (comment[2] != "" && comment[2] != null) {
				Map<String, Object> commentary = new HashMap<>();
				commentary.put("id", comment[0]);
				commentary.put("username", comment[1]);
				commentary.put("comentary", comment[2]);
				commentary.put("createdAt", comment[3]);
				commentByHero.add(commentary);
			}
		}

		return commentByHero;

	}
	
	@PostMapping("/suggestion")
	public Suggestion saveContact(@RequestBody Suggestion suggestion) {
		return suggestionRepository.save(suggestion);
	}
}
