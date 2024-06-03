package csc340project.example.springio.User.OwnedGame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
import csc340project.example.springio.GameListing.GameListing;
import csc340project.example.springio.GameListing.GameListingService;
 **/

@RestController
@RequestMapping("/user/ownedGames")
public class OwnedGameController {
    @Autowired
    private OwnedGameService ownedGameService;

    @GetMapping("/")
    public ResponseEntity<List<OwnedGame>> getAllOwnedGames() {
        List<OwnedGame> ownedGames = ownedGameService.getAllOwnedGames();
        return ResponseEntity.ok(ownedGames);
    }

    @PostMapping("/")
    public ResponseEntity<OwnedGame> createOwnedGame(@RequestBody OwnedGame ownedGame) {
        OwnedGame savedOwnedGame = ownedGameService.saveOwnedGame(ownedGame);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOwnedGame);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnedGame> getOwnedGameById(@PathVariable Integer id) {
        Optional<OwnedGame> ownedGame = ownedGameService.getOwnedGameById(id);
        return ownedGame.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwnedGame(@PathVariable Integer id) {
        ownedGameService.deleteOwnedGame(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

