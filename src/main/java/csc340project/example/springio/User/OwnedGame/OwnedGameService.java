package csc340project.example.springio.User.OwnedGame;


import csc340project.example.springio.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnedGameService {
    @Autowired
    private OwnedGameRepository ownedGameRepository;

/** TODO: waiting on game listing
    @Autowired
    private GameListingRepository gameListingRepository;
**/
    @Autowired
    private UserRepository userRepository;

    public List<OwnedGame> getAllOwnedGames() {
        return ownedGameRepository.findAll();
    }

    public OwnedGame saveOwnedGame(OwnedGame ownedGame) {
        return ownedGameRepository.save(ownedGame);
    }

    public Optional<OwnedGame> getOwnedGameById(Integer id) {
        return ownedGameRepository.findById(id);
    }

    public void deleteOwnedGame(Integer id) {
        ownedGameRepository.deleteById(id);
    }

/** TODO: waiting on game listing
    public Optional<OwnedGame> createOwnedGameWithGameListing(Integer userId, Integer gameListingId, OwnedGame ownedGame) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            ownedGame.setUser(user.get());
            Optional<GameListing> gameListing = gameListingRepository.findById(gameListingId);
            if (gameListing.isPresent()) {
                ownedGame.setGameListing(gameListing.get());
                return Optional.of(ownedGameRepository.save(ownedGame));
            }
        }
        return Optional.empty();
    }
**/
}
