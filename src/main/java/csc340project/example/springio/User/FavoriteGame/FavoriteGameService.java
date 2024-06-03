package csc340project.example.springio.User.FavoriteGame;

import csc340project.example.springio.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteGameService {
    @Autowired
    private FavoriteGameRepository favoriteGameRepository;
/** TODO: waiting for game listing
    @Autowired
    private GameListingRepository gameListingRepository;
**/
    @Autowired
    private UserRepository userRepository;

    public List<FavoriteGame> getAllFavoriteGames() {
        return favoriteGameRepository.findAll();
    }

    public FavoriteGame saveFavoriteGame(FavoriteGame favoriteGame) {
        return favoriteGameRepository.save(favoriteGame);
    }

    public Optional<FavoriteGame> getFavoriteGameById(Integer id) {
        return favoriteGameRepository.findById(id);
    }

    public void deleteFavoriteGame(Integer id) {
        favoriteGameRepository.deleteById(id);
    }

/** TODO: waiting for game listing
    public Optional<FavoriteGame> createFavoriteGameWithGameListing(Integer userId, Integer gameListingId, FavoriteGame favoriteGame) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            favoriteGame.setUser(user.get());
            Optional<GameListing> gameListing = gameListingRepository.findById(gameListingId);
            if (gameListing.isPresent()) {
                favoriteGame.setGameListing(gameListing.get());
                return Optional.of(favoriteGameRepository.save(favoriteGame));
            }
        }
        return Optional.empty();
    }
    **/
}
