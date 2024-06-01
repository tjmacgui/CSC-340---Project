package csc340project.example.springio.GroupListings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupListingService {
    @Autowired
    GroupListingRepository groupListingRepository;

    @Autowired
    GroupMemberRepository groupMemberRepository;

    public GroupListing getGroupListingById(int id) {
        return groupListingRepository.getGroupListingById(id);
    }

    public List<GroupListing> getAllGroupListingsForGame(int gameId) {
        return groupListingRepository.getAllGroupListingsForGame(gameId);
    }

    public List<GroupListing> getGroupsBySearch(String searchInput, int gameId) {
        return groupListingRepository.getAllGroupListingsFromTitle(gameId, searchInput);
    }

    public boolean userInGroup(int userId, int groupId) {
        return groupMemberRepository.userExistsInGroup(userId, groupId);
    }

    public boolean userIsOwner(int userId, int groupId) {
        GroupListing group = groupListingRepository.getGroupListingById(groupId);
        return group.getOwnerId() == userId;
    }

    public void addNewGroupListing(GroupListing groupListing) {
        groupListingRepository.save(groupListing);
    }

    public void removeGroupListingById(int groupId) {
        groupListingRepository.deleteById(groupId);
    }

    public void updateGroupListing(GroupListing groupListing) {
        groupListingRepository.save(groupListing);
    }
}
