package com.quiz.whiz;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.quiz.whiz.entities.Categorie;



@SpringBootApplication
public class QuizWhizApplication {

	public static void main(String[] args) {

		SpringApplication.run(QuizWhizApplication.class, args);
		
	}
	 public void run(String... args) throws Exception { 
	  System.out.println("game started successfully!");
	 } 
}
