package com.quiz.whiz;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.quiz.whiz.DTO.PartieDTO;
import com.quiz.whiz.entities.Partie;
import com.quiz.whiz.repositories.PartieRepository;

@SpringBootTest
class QuizWhizApplicationTests {

    @Autowired
    private PartieRepository partieRepository;
@Test
	   @PostMapping("/new")
	    public Partie createNewPartie(@RequestBody Partie partie) {
	        return partieRepository.save(partie);
	    }

}


