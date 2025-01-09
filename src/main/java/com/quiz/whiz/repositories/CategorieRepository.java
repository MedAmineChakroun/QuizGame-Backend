package com.quiz.whiz.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.whiz.entities.Categorie;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie,Long> {
  


}
