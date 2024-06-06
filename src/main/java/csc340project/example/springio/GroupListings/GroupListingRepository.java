package csc340project.example.springio.GroupListings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface GroupListingRepository extends JpaRepository<GroupListing, Integer> {
    @Query(value = "select * from group_listings where group_listing_id = ?1", nativeQuery = true)
    public GroupListing getGroupListingById(int id);

    @Query(value = "select * from group_listings where listing_id = ?1", nativeQuery = true)
    public List<GroupListing> getAllGroupListingsForGame(int gameId);

    @Query(value = "select * from group_listings where listing_id = ?1 and title like ?2%", nativeQuery = true)
    public List<GroupListing> getAllGroupListingsFromTitle(int gameId, String title);
}
