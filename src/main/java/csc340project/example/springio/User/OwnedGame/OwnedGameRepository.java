package csc340project.example.springio.User.OwnedGame;

import csc340project.example.springio.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnedGameRepository extends JpaRepository<OwnedGame, Integer> {
    List<OwnedGame> findByUser(User user);
}