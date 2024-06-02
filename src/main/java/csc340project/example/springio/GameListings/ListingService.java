package csc340project.example.springio.GameListings;

import org.springframework.beans.factory.annotation.Autowired;

public class ListingService {
    @Autowired ListingRepo listingRepo;

    public Listing getGameListingById(int gameId) {
        return listingRepo.findById(gameId).orElse(null);
    }

    public Listing getName(){

    }

    public Listing{

    }
}
