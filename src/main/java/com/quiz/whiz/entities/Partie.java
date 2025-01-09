package com.quiz.whiz.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data

public class Partie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("questionReached")
    private int questionReached;

    private int nbHeart;

    @ManyToOne
    @JoinColumn(name ="idCategorie")

    private Categorie categorie;

    @ManyToOne
    @JoinColumn(name = "idPlayer")
    private Player player;

    //test1
	public Partie(Categorie categorie, Player player) {
		super();
		this.categorie = categorie;
		this.player = player;
		this.questionReached = 1;
		this.nbHeart = 3;

	}

	public Partie(){
        this.questionReached = 1;
        this.nbHeart = 3;
    }



}