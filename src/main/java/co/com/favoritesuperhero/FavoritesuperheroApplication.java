package co.com.favoritesuperhero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FavoritesuperheroApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavoritesuperheroApplication.class, args);
	}

}
