package csc340project.example.springio.User.FavoriteGame;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteGameRepository extends JpaRepository<FavoriteGame, Integer> {
}
