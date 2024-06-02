package csc340project.example.springio.GroupListings;

import csc340project.example.springio.GroupMember.GroupMember;
import csc340project.example.springio.GroupMember.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupListingService {
    @Autowired
    GroupListingRepository groupListingRepository;

    @Autowired
    GroupMemberService groupMemberService;

    public GroupListing getGroupListingById(int id) {
        return groupListingRepository.getGroupListingById(id);
    }

    public List<GroupListing> getAllGroupListingsForGame(int gameId) {
        return groupListingRepository.getAllGroupListingsForGame(gameId);
    }

    public List<GroupListing> getGroupsBySearch(String searchInput, int gameId) {
        return groupListingRepository.getAllGroupListingsFromTitle(gameId, searchInput);
    }

    /**
     * Checks if user exists in the given group listing.
     * @param userId Specified user ID
     * @param groupId Specified group ID
     * @return returns true if exists else false
     */
    public boolean userInGroup(int userId, int groupId) {
        return groupMemberService.userExistsInGroup(userId, groupId);
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

    public void removeMember(int memberId, int groupId) {
        groupMemberService.removeMember(memberId, groupId);
    }

    public void addNewMember(int groupId, int userId) {
        GroupMember groupMember = new GroupMember(userId, false, groupId);
        groupMemberService.addNewMember(groupMember);
    }
}
