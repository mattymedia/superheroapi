package co.com.favoritesuperhero.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "hero_vote")
@Data
public class HeroVote implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "id_hero")
	private Integer idHero;
	
	private String username;
	
	private String comentary;
	
	@Column(name = "create_at")
	private LocalDate createdAt;
	
	@PrePersist
	public void actualDate() {
		createdAt = LocalDate.now();
	}
	
	private static final long serialVersionUID = 1L;	
}