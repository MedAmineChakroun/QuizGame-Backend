package com.quiz.whiz.repositories;


import com.quiz.whiz.entities.Hint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HintRepository extends JpaRepository<Hint,Long> {
}
