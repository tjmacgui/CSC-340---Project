package csc340project.example.springio.GameListings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ListingRepo extends JpaRepository<Listing, Integer> {
    @Query("SELECT l FROM Listing l WHERE l.listingId IN :listingIds")
    List<Listing> findAllByListingIds(List<Integer> listingIds);
}
