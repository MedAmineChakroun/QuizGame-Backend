package com.quiz.whiz.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.quiz.whiz.entities.Partie;

import java.util.List;


@Repository
public interface PartieRepository extends JpaRepository<Partie, Long>{

    List<Partie> findByPlayerIdAndCategorieId(Long player, Long categorie);

}
