package com.quiz.whiz.controllers;

import com.quiz.whiz.entities.Categorie;
import com.quiz.whiz.entities.Hint;
import com.quiz.whiz.entities.Partie;
import com.quiz.whiz.entities.Player;
import com.quiz.whiz.repositories.CategorieRepository;
import com.quiz.whiz.repositories.PartieRepository;
import com.quiz.whiz.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.quiz.whiz.entities.HintType.*;

@RestController
@RequestMapping("/parties")
@CrossOrigin(origins = "*")
public class PartieController {

    @Autowired
    private PartieRepository partieRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Partie> getPartieById(@PathVariable Long id) {
        Optional<Partie> partieOpt = partieRepository.findById(id);
        return partieOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/createNewPartie")
    public ResponseEntity<?> createNewPartie(
            @RequestParam String firebaseId,
            @RequestParam Long categoryId) {

        // Retrieve Player by firebaseId
        Optional<Player> playerOpt = playerRepository.findByFirebaseUid(firebaseId);
        if (!playerOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Player with firebaseId " + firebaseId + " not found.");
        }

        // Retrieve Categorie by categoryId
        Optional<Categorie> categorieOpt = categorieRepository.findById(categoryId);
        if (!categorieOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Categorie with categoryId " + categoryId + " not found.");
        }

        // Create a new Partie
        Player player = playerOpt.get();
        Categorie categorie = categorieOpt.get();

        Partie newPartie = new Partie();
        newPartie.setPlayer(player);
        newPartie.setCategorie(categorie);

        //List<Hint> defaultHints = new ArrayList<>();
        //defaultHints.add(new Hint(newPartie,ELIMINATE_TWO_OPTIONS));
        //defaultHints.add(new Hint(newPartie,SHOW_CORRECT_ANSWER));
        //defaultHints.add(new Hint(newPartie,ADD_MORE_TIME));
        //newPartie.setHints(defaultHints);

        Partie savedPartie = partieRepository.save(newPartie);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPartie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePartieById(@PathVariable Long id) {
        try {
            partieRepository.deleteById(id);
            return ResponseEntity.ok("Partie deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting Partie.");
        }
    }
    @GetMapping("/exists")
    public ResponseEntity<Partie> checkPartieExists(
            @RequestParam String firebaseId,
            @RequestParam Long categoryId) {

        // Retrieve Player by firebaseId
        Optional<Player> playerOpt = playerRepository.findByFirebaseUid(firebaseId);
        if (!playerOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Retrieve Categorie by categoryId
        Optional<Categorie> categorieOpt = categorieRepository.findById(categoryId);
        if (!categorieOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Check if Partie exists
        Player player = playerOpt.get();
        Categorie categorie = categorieOpt.get();
        List<Partie> existingParties = partieRepository.findByPlayerIdAndCategorieId(player.getId(), categorie.getId());

        if (!existingParties.isEmpty()) {
            // If the Partie exists, return it
            Partie existingPartie = existingParties.get(0); // Get the first existing Partie
            return ResponseEntity.ok(existingPartie);
        } else {
            // If no Partie exists, return OK with null body
            return ResponseEntity.ok().body(null);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePartie(
            @PathVariable Long id,
            @RequestBody Partie updateRequest) {

        Optional<Partie> partieOpt = partieRepository.findById(id);
        if (!partieOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Partie not found.");
        }

        Partie partie = partieOpt.get();

        // Conditionally update fields if provided in the request
        if (updateRequest.getQuestionReached() != 0) {
            partie.setQuestionReached(updateRequest.getQuestionReached());
        }
        if (updateRequest.getNbHeart() != -1) {
            partie.setNbHeart(updateRequest.getNbHeart());
        }

        Partie updatedPartie = partieRepository.save(partie);
        return ResponseEntity.ok(updatedPartie);
    }






}
