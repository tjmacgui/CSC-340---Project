package csc340project.example.springio.User.OwnedGame;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnedGameRepository extends JpaRepository<OwnedGame, Integer> {
}
