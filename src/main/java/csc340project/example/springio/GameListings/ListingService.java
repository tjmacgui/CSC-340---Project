package csc340project.example.springio.GameListings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingService {
    @Autowired
    ListingRepo listingRepo;

    public Listing getGameListingById(int gameId) {
        return listingRepo.findById(gameId).orElse(null);
    }

    public Listing getListingByName(String gameTitle) {
        return listingRepo.findListingByTitle(gameTitle);
    }

    public List<Listing> getAllListings() {
        return listingRepo.findAll();
    }
}
