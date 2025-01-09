package com.quiz.whiz.controllers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.quiz.whiz.entities.Player;
import com.quiz.whiz.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private FirebaseAuth firebaseAuth;

    @Autowired
    private PlayerRepository playerRepository;

    // Endpoint to verify Firebase ID token and create or fetch player
    @PostMapping("/verifyToken")
    public ResponseEntity<AuthResponse> verifyToken(@RequestBody Map<String, String> body) {
        try {
            String idToken = body.get("idToken");  // Get the ID token from the request body
            if (idToken == null || idToken.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new AuthResponse("ID token is required.", null));
            }

            // Verify the ID token and decode the user info
            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(idToken);
            String firebaseUid = decodedToken.getUid(); // Get the Firebase UID

            // Check if the player exists in the database by Firebase UID
            Player player = playerRepository.findByFirebaseUid(firebaseUid).orElse(null);

            // If the player does not exist, create a new player
            if (player == null) {
                player = new Player(decodedToken.getName(), decodedToken.getEmail(), firebaseUid);
                playerRepository.save(player);
            }

            // Return success message with player details (playerId)
            return ResponseEntity.ok(new AuthResponse("User authenticated successfully", player.getId()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse("Authentication failed: " + e.getMessage(), null));
        }
    }

    // Define a response class to send the message and playerId
    public static class AuthResponse {
        private String message;
        private Long playerId;

        public AuthResponse(String message, Long playerId) {
            this.message = message;
            this.playerId = playerId;
        }

        public String getMessage() {
            return message;
        }

        public Long getPlayerId() {
            return playerId;
        }
    }
}
