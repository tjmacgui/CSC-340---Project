package csc340project.example.springio.GroupListings;

import csc340project.example.springio.GameListings.Listing;
import csc340project.example.springio.GameTags.Tag;
import csc340project.example.springio.GameTags.TagService;
import csc340project.example.springio.GroupMember.GroupMember;
import csc340project.example.springio.GroupMember.GroupMemberService;
import csc340project.example.springio.User.User;
import csc340project.example.springio.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupListingService {
    @Autowired
    GroupListingRepository groupListingRepository;

    @Autowired
    GroupMemberService groupMemberService;

    @Autowired
    UserService userService;

    @Autowired
    static TagService tagService;

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
        return group.getOwnerId().getUserId() == userId;
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
        Optional<User> userOptional = userService.getUserById(userId);
        User user;
        if (userOptional.isPresent())
            user = userOptional.get();
        else
            return;
        GroupListing group = groupListingRepository.getGroupListingById(groupId);

        GroupMember groupMember = new GroupMember(group, user);
        groupMemberService.addNewMember(groupMember);
    }

    /**
     * Helper method linking tag service for tag initialization in group listing objects.
     * @param listingId id of the game listing
     * @return list of available tags for the inserted game
     */
    public static List<Tag> getTagListForGame(int listingId) {
        return tagService.getAllTagsForGame(listingId);
    }
}
