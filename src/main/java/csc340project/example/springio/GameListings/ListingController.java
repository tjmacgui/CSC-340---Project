package csc340project.example.springio.GameListings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequestMapping("/games")
public class ListingController {
    @Autowired
    private ListingService listingService;


    @GetMapping({"/", "/view"})
    public ResponseEntity<List<Listing>> getAllListings() {
        List<Listing> Listings = listingService.getAllListings();
        return ResponseEntity.ok(Listings);
    }

    @PostMapping("/")
    public ResponseEntity<Listing> createListing(@RequestBody Listing Listing) {
        Listing savedListing = listingService.saveListing(Listing);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedListing);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Listing> getListingById(@PathVariable Integer id) {
        Optional<Listing> listing = listingService.getListingsById(id);
        return listing.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteListing(@PathVariable Integer id) {
        listingService.deleteListing(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
