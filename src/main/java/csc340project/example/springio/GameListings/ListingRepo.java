package csc340project.example.springio.GameListings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ListingRepo extends JpaRepository<Listing, Integer> {
    @Query(value = "select * from game-listings where title like ?1%", nativeQuery = true)
    public Listing findListingByTitle(String title);
}
