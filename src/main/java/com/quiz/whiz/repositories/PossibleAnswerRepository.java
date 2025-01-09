package com.quiz.whiz.repositories;



import com.quiz.whiz.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;


import com.quiz.whiz.entities.PossibleAnswer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PossibleAnswerRepository extends JpaRepository<PossibleAnswer, Long> {

    List<PossibleAnswer> findByQuestion_Id(Long questionId);

}
