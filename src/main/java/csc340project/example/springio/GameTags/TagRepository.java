package csc340project.example.springio.GameTags;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    @Query(value = "select * from game_tags where listing_id = ?1", nativeQuery = true)
    public List<Tag> getAllTagsForGame(int listingId);

    @Query(value = "select tag_name from game_tags where listing_id = ?1", nativeQuery = true)
    public List<String> getAllTagNamesForGame(int listingId);
}
