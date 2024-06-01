package csc340project.example.springio.GroupListings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupListingService {
    @Autowired
    GroupListingRepository groupListingRepository;

    public GroupListing getGroupListingById(int id) {
        return groupListingRepository.getGroupListingById(id);
    }

    public List<GroupListing> getAllGroupListingsForGame(int gameId) {
        return groupListingRepository.getAllGroupListingsForGame(gameId);
    }

    public List<GroupListing> getGroupsBySearch(String searchInput, int gameId) {
        return groupListingRepository.getAllGroupListingsFromTitle(gameId, searchInput);
    }
}
