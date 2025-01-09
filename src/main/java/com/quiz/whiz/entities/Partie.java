package com.quiz.whiz.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static com.quiz.whiz.entities.HintType.*;

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

    @OneToMany(mappedBy = "partie", cascade = CascadeType.ALL)
    private List<Hint> hints;
    //test1
	public Partie(Categorie categorie, Player player) {
		super();
		this.categorie = categorie;
		this.player = player;
		this.questionReached = 1;
		this.nbHeart = 3;
        this.hints = new ArrayList<>();
        this.hints.add(new Hint(this, ELIMINATE_TWO_OPTIONS));
        this.hints.add(new Hint(this, SHOW_CORRECT_ANSWER));
        this.hints.add(new Hint(this, ADD_MORE_TIME));

	}

	public Partie(){
        this.questionReached = 1;
        this.nbHeart = 3;
        this.hints = new ArrayList<>();
        this.hints.add(new Hint(this, ELIMINATE_TWO_OPTIONS));
        this.hints.add(new Hint(this, SHOW_CORRECT_ANSWER));
        this.hints.add(new Hint(this, ADD_MORE_TIME));
    }



}