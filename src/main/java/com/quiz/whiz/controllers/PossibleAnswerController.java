package com.quiz.whiz.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.quiz.whiz.entities.PossibleAnswer;
import com.quiz.whiz.entities.Question;
import com.quiz.whiz.repositories.PossibleAnswerRepository;

@RestController
@RequestMapping("/possibleAnswers")
@CrossOrigin(origins = "*")
public class PossibleAnswerController {
    @Autowired
    private  PossibleAnswerRepository  possibleAnswerRepository;

    @GetMapping()
    public List<PossibleAnswer> getAllPossibleAnswers() {
        return possibleAnswerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<PossibleAnswer> getPossibleAnswersById(@PathVariable Long id) {
        return possibleAnswerRepository.findById(id);
    }
    @GetMapping("/question/{questionId}")
    public List<PossibleAnswer> getPossibleAnswersByQuestionId(@PathVariable Long questionId) {
        return possibleAnswerRepository.findByQuestion_Id(questionId);
    }

}
