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
@Table(name = "suggestion")
@Data
public class Suggestion implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String username;
		
	private String message;
	
	@Column(name="create_at")
	private LocalDate createAt;
	
	@PrePersist
	public void actualDate() {
		createAt = LocalDate.now();
	}
	
	private static final long serialVersionUID = 1L;	
}
