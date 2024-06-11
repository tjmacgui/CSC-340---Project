package csc340project.example.springio.User.OwnedGame;

import csc340project.example.springio.GameListings.Listing;
import csc340project.example.springio.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OwnedGameRepository extends JpaRepository<OwnedGame, Integer> {
    List<OwnedGame> findByUser(User user);
    Optional<OwnedGame> findByUserAndListing(User user, Listing listing);
}