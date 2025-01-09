package com.quiz.whiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.whiz.entities.Player;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository <Player, Long> {

    Optional<Player> findByEmail(String email);
    Optional<Player> findByFirebaseUid(String firebaseUid);

}