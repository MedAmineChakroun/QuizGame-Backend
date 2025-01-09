package com.quiz.whiz.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Hint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@ManyToOne
    //@JsonBackReference("partieReference")
   // @JoinColumn(name = "partie_id", nullable = false)
   // private Partie partie;

    @Enumerated(EnumType.STRING)
    private HintType hintType;

    private Integer remainingHints;

     public Hint(Partie partie, HintType hintType){
       // this.partie = partie;
        this.hintType = hintType;
        this.remainingHints=1;
    }
    public Hint(){
        this.remainingHints=1;
    }

}

