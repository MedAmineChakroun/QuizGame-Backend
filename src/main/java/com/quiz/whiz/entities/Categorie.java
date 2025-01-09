package com.quiz.whiz.entities;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categorieName;

    private String categorieDescription;

    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL)
    @JsonBackReference
    private Collection<Partie> parties;

    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL)

    private Collection<Question> questions;

}
