package com.quiz.whiz.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(
		name = "player",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = {"userName"}),
				@UniqueConstraint(columnNames = {"email"})
		}
)
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String userName;
	private String email;
	private String firebaseUid; // Store Firebase UID here
	private int gold;

	@OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Partie> parties;

	public Player(String userName, String email, String firebaseUid) {
		this.userName = userName;
		this.email = email;
		this.firebaseUid = firebaseUid;
		this.gold = 0;
	}
}
