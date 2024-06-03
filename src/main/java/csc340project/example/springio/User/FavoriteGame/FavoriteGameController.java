package csc340project.example.springio.User.FavoriteGame;

import csc340project.example.springio.GameListings.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// TODO: waiting on game listing import csc340project.example.springio.GameListing.GameListingService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/favoriteGames")
public class FavoriteGameController {
    @Autowired
    private FavoriteGameService favoriteGameService;

    @Autowired
    private ListingService gameListingService;

    @GetMapping
    public String getAllFavoriteGames() {
        List<FavoriteGame> favoriteGames = favoriteGameService.getAllFavoriteGames();
        StringBuilder response = new StringBuilder();
        for (FavoriteGame favoriteGame : favoriteGames) {
            response.append(favoriteGame.getId()).append(", ")
                    .append(favoriteGame.getListingId().getTitle()).append(", ")
                    .append(favoriteGame.getUser().getUserId()).append("\n");
        }
        return response.toString();
    }

    @PostMapping
    public String createFavoriteGame(@RequestBody FavoriteGame favoriteGame) {
        FavoriteGame savedFavoriteGame = favoriteGameService.saveFavoriteGame(favoriteGame);
        return "FavoriteGame created with ID: " + savedFavoriteGame.getId();
    }

    @PostMapping("/user/{userId}/gameListing/{gameListingId}")
    public String createFavoriteGameWithGameListing(
            @PathVariable Integer userId,
            @PathVariable Integer gameListingId,
            @RequestBody FavoriteGame favoriteGame) {
        Optional<FavoriteGame> result = favoriteGameService.createFavoriteGameWithGameListing(userId, gameListingId, favoriteGame);

        return result.map(value -> "FavoriteGame created with ID: " + value.getId())
                .orElse("User or GameListing not found");
    }

    @GetMapping("/{id}")
    public String getFavoriteGameById(@PathVariable Integer id) {
        Optional<FavoriteGame> favoriteGame = favoriteGameService.getFavoriteGameById(id);
        return favoriteGame.map(value -> value.getId() + ", " +
                value.getListingId().getTitle() + ", " +
                value.getUser().getUserId()).orElse("FavoriteGame not found");
    }

    @DeleteMapping("/{id}")
    public String deleteFavoriteGame(@PathVariable Integer id) {
        favoriteGameService.deleteFavoriteGame(id);
        return "FavoriteGame with ID " + id + " deleted";
    }

}
