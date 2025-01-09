package com.quiz.whiz.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.whiz.entities.Categorie;
import com.quiz.whiz.repositories.CategorieRepository;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*") // Allow requests from all origins, you can customize this for security purposes.
public class CategorieController {

    @Autowired
    private CategorieRepository categorierepository;

    // Get all categories
    @GetMapping
    public List<Categorie> getAllCategories() {
        return categorierepository.findAll();
    }

    // Get category by ID
    @GetMapping("/{id}")
    public ResponseEntity<Categorie> getCategorieById(@PathVariable Long id) {
        Optional<Categorie> categorie = categorierepository.findById(id);
        return categorie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Add new category
    @PostMapping
    public ResponseEntity<Categorie> createCategorie(@RequestBody Categorie categorie) {
        Categorie newCategorie = categorierepository.save(categorie);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategorie);
    }

    // Update an existing category
    @PutMapping("/{id}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable Long id, @RequestBody Categorie categorie) {
        if (!categorierepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categorie.setId(id);  // Ensure the ID of the category is set for updating
        Categorie updatedCategorie = categorierepository.save(categorie);
        return ResponseEntity.ok(updatedCategorie);
    }

    // Delete category by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable Long id) {
        if (!categorierepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categorierepository.deleteById(id);
        return ResponseEntity.noContent().build(); // Return 204 No Content
    }
}
