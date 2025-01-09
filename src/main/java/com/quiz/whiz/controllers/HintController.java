package com.quiz.whiz.controllers;

import com.quiz.whiz.entities.Hint;
import com.quiz.whiz.repositories.HintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hints")
@CrossOrigin(origins = "*")
public class HintController {

    @Autowired
    private HintRepository hintRepository;

    // 1. Get all hints
    @GetMapping
    public List<Hint> getAllHints() {
        return hintRepository.findAll();
    }

    // 2. Get hint by ID
    @GetMapping("/{id}")
    public ResponseEntity<Hint> getHintById(@PathVariable Long id) {
        Optional<Hint> hint = hintRepository.findById(id);
        return hint.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3. Create a new hint
    @PostMapping
    public Hint createHint(@RequestBody Hint hint) {
        return hintRepository.save(hint);
    }

    // 4. Delete a hint by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHint(@PathVariable Long id) {
        if (hintRepository.existsById(id)) {
            hintRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hint> updateHint(@PathVariable Long id, @RequestBody Hint hintDetails) {
        return hintRepository.findById(id).map(hint -> {
            // Update fields as necessary
            hint.setRemainingHints(hintDetails.getRemainingHints());

            Hint updatedHint = hintRepository.save(hint);
            return ResponseEntity.ok(updatedHint);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
