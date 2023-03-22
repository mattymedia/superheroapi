package co.com.favoritesuperhero.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "heroes", url = "64064eb377c1a905a0d8eb1d.mockapi.io")
public interface IHeroApiClient {

	@GetMapping("/superhero")
	public List<Map<String, Object>> findAllHeroes();
}
