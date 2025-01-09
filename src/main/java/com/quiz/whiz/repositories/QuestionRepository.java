package com.quiz.whiz.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.whiz.entities.Categorie;
import com.quiz.whiz.entities.Question;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    // Existing method to find by question ID
    Optional<Question> findById(Long id);

    // Existing method to find questions by category
    List<Question> findByCategorie(Categorie categorie);

    // New method to find questions by category ID
    List<Question> findByCategorieId(Long categorieId);


}

