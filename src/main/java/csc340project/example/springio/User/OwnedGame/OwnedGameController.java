package csc340project.example.springio.User.OwnedGame;

import csc340project.example.springio.GameListings.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
import csc340project.example.springio.GameListing.GameListing;
import csc340project.example.springio.GameListing.GameListingService;
 **/
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/ownedGames")
public class OwnedGameController {
    @Autowired
    private OwnedGameService ownedGameService;

    @Autowired
    private ListingService listingService;

//    @GetMapping
//    public String getAllOwnedGames() {
//        List<OwnedGame> ownedGames = ownedGameService.getAllOwnedGames();
//        StringBuilder response = new StringBuilder();
//        for (OwnedGame ownedGame : ownedGames) {
//            response.append(ownedGame.getId()).append(", ")
//                    .append(ownedGame.getListingId().getGameName()).append(", ")
//                    .append(ownedGame.getUser().getUserId()).append("\n");
//        }
//        return response.toString();
//    }

    @PostMapping
    public String createOwnedGame(@RequestBody OwnedGame ownedGame) {
        OwnedGame savedOwnedGame = ownedGameService.saveOwnedGame(ownedGame);
        return "OwnedGame created with ID: " + savedOwnedGame.getId();
    }
/** TODO: waiting on game listing

 @PostMapping("/user/{userId}/gameListing/{gameListingId}")
 public String createOwnedGameWithGameListing(
 @PathVariable Integer userId,
 @PathVariable Integer gameListingId,
 @RequestBody OwnedGame ownedGame) {
 Optional<OwnedGame> result = ownedGameService.createOwnedGameWithGameListing(userId, gameListingId, ownedGame);

 return result.map(value -> "OwnedGame created with ID: " + value.getId())
 .orElse("User or GameListing not found");
 }


    @GetMapping("/{id}")
    public String getOwnedGameById(@PathVariable Integer id) {
        Optional<OwnedGame> ownedGame = ownedGameService.getOwnedGameById(id);
        return ownedGame.map(value -> value.getId() + ", " +
                value.getListingId().getGameName() + ", " +
                value.getUser().getUserId()).orElse("OwnedGame not found");
    }
**/
    @DeleteMapping("/{id}")
    public String deleteOwnedGame(@PathVariable Integer id) {
        ownedGameService.deleteOwnedGame(id);
        return "OwnedGame with ID " + id + " deleted";
    }

}
