package csc340project.example.springio.User.OwnedGame;

import csc340project.example.springio.GameListings.Listing;
import csc340project.example.springio.GameListings.ListingRepo;
import csc340project.example.springio.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO: waiting on game listing import csc340project.example.springio.GameListing.GameListingRepository;
import csc340project.example.springio.User.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OwnedGameService {
    @Autowired
    private OwnedGameRepository ownedGameRepository;

    @Autowired
    ListingRepo listingRepo;


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


    public Optional<OwnedGame> createOwnedGameWithGameListing(Integer userId, Integer gameListingId, OwnedGame ownedGame) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            ownedGame.setUser(user.get());
            Optional<Listing> gameListing = listingRepo.findById(gameListingId);
            if (gameListing.isPresent()) {
                ownedGame.setListingId(gameListing.get());
                return Optional.of(ownedGameRepository.save(ownedGame));
            }
        }
        return Optional.empty();
    }

}
