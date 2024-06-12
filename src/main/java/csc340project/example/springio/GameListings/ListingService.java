package csc340project.example.springio.GameListings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListingService {
    @Autowired
    ListingRepo listingRepo;

    public Listing getGameListingById(int gameId) {
        return listingRepo.findById(gameId).orElse(null);
    }

    public Listing getListingByName(Integer id) {
        return listingRepo.findById(id).orElse(null);
    }

    public List<Listing> getAllListings() {
        return listingRepo.findAll();
    }
    public Listing saveListing(Listing listing) {
        return listingRepo.save(listing);
    }

    public void deleteListing(Integer id){
        listingRepo.deleteById(id);
    }

    public List<Listing> getListingsByIds(List<Integer> ids) {
        return listingRepo.findAllById(ids);
    }
    }

