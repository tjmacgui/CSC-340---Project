package csc340project.example.springio.GameListings;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepo extends JpaRepository<Listing, Integer> {

}
