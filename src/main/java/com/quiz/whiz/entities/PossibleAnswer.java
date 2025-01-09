package com.quiz.whiz.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class PossibleAnswer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String possibleAnswer;

	@ManyToOne
	@JoinColumn(name = "idquestion")  // Ensure this matches the column in the database.
	@JsonBackReference // Prevent infinite recursion from Question -> PossibleAnswer -> Question
	private Question question;

	public PossibleAnswer(String possibleAnswer, Question question) {
		super();
		this.possibleAnswer = possibleAnswer;
		this.question = question;
	}
	PossibleAnswer(){

	}
}
