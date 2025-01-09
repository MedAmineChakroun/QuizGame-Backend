package com.quiz.whiz.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String correctAnswer;
    private int goldQuestion;


    @ManyToOne
    @JoinColumn(name = "id_categorie")
    @JsonBackReference // Prevent infinite recursion from Question -> PossibleAnswer -> Question
    private Categorie categorie;


    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
   // Prevent infinite recursion from Question -> PossibleAnswer -> Question
    private List<PossibleAnswer> possibleAnswers;


	public Question(String content, String correctAnswer, int goldQuestion,  Categorie categorie,
			List<PossibleAnswer> possibleAnswers) {
		super();
		this.content = content;
		this.correctAnswer = correctAnswer;
		this.goldQuestion = goldQuestion;

		this.categorie = categorie;
		this.possibleAnswers = possibleAnswers;
	}
    public Question(){

    }

}
