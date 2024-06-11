package csc340project.example.springio.GroupListings;

import csc340project.example.springio.GameListings.Listing;
import csc340project.example.springio.GameListings.ListingService;
import csc340project.example.springio.GameTags.Tag;
import csc340project.example.springio.GameTags.TagService;
import csc340project.example.springio.GroupMember.GroupMemberService;
import csc340project.example.springio.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user/{userId}/game/{gameId}/groups")
public class GroupListingController {
    private static final int USERNOTLOGGEDIN = 0;   //this is temporary, if a user is not logged in their userId in
                                                    // the url will be 0
    @Autowired
    GroupListingService groupListingService;

    @Autowired
    ListingService gameListingService;

    @Autowired
    UserService userService;

    @Autowired
    TagService tagService;

    @Autowired
    GroupMemberService groupMemberService;

    private String requestString(String userId, String gameId) {
        return "/user/" + userId + "/game/" + gameId + "/groups";
    }

    @GetMapping("/")
    public String viewAllGroupListings(@PathVariable("gameId") String gameIdString, Model model) {
        int gameId = Integer.parseInt(gameIdString);

        Listing game = gameListingService.getGameListingById(gameId);
        if (game.getGameImageURL() == null) {
            game.setGameImageURL("https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Video-Game-Controller-Icon-D-Edit.svg/2048px-Video-Game-Controller-Icon-D-Edit.svg.png");
        }

        model.addAttribute("gameListing", game);
        model.addAttribute("groupListings", groupListingService.getAllGroupListingsForGame(gameId));

        return "group-listings";
    }

    @GetMapping("/search/{searchInput}")
    public String sortGroupListingsBySearch(@PathVariable("gameId") String gameIdString, @PathVariable("searchInput") String searchInput, Model model) {
        int gameId = Integer.parseInt(gameIdString);
        model.addAttribute("game", gameListingService.getGameListingById(gameId));
        model.addAttribute("groupListings", groupListingService.getGroupsBySearch(searchInput, gameId));
        return "group-listings";
    }

    @GetMapping("/{groupId}/join")
    public String userJoinGroup(@PathVariable("userId") String userIdString, @PathVariable("gameId") String gameIdString, @PathVariable("groupId") String groupIdString, Model model) {
        int gameId = Integer.parseInt(gameIdString);
        int userId = Integer.parseInt(userIdString);
        int groupId = Integer.parseInt(groupIdString);

        //login redirection
        if (userId == USERNOTLOGGEDIN) {
            String redirectionUrl = requestString(userIdString, gameIdString) + "/" + groupIdString + "/join";
            model.addAttribute("redirectionUrl", redirectionUrl);
            return "user-account-login";
        } else {
            GroupListing selectedGroupListing = groupListingService.getGroupListingById(groupId);
            if (selectedGroupListing.getOpenMemberSpots() == 0) {
                model.addAttribute("errorMessage", new GroupListingError(GroupListingError.ErrorType.JOIN_FULL));   //the group is full
                return "redirect:" + requestString(userIdString, gameIdString) + "/";
            } else if (groupListingService.userInGroup(userId, groupId)) {
                model.addAttribute("errorMessage", new GroupListingError(GroupListingError.ErrorType.JOIN_INGROUP));    //user is already in the group
                return "redirect:" + requestString(userIdString, gameIdString) + "/";
            } else {
                model.addAttribute("successMessage", new GroupListingSuccess(GroupListingSuccess.SuccessType.JOIN));    //user successfully joins the group
                groupListingService.addNewMember(groupId, userId);
                selectedGroupListing.memberJoin();
                groupListingService.updateGroupListing(selectedGroupListing);
                return "redirect:" + requestString(userIdString, gameIdString) + "/";
            }
        }
    }

    @PostMapping("/{groupId}/update")
    public String updateGroup(@PathVariable("userId") String userIdString, @PathVariable("gameId") String gameIdString, @PathVariable("groupId") String groupIdString, @RequestBody GroupListing groupListing, Model model) {
        int gameId = Integer.parseInt(gameIdString);
        int userId = Integer.parseInt(userIdString);
        int groupId = Integer.parseInt(groupIdString);

        groupListing.setlistingId(gameListingService.getGameListingById(gameId));
        groupListing.setOwnerId(userService.getUserById(userId).get());
        groupListing.setGroupListingId(groupId);

        model.addAttribute("successMessage", new GroupListingSuccess(GroupListingSuccess.SuccessType.UPDATE));
        return "redirect:" + requestString(userIdString, gameIdString) + "/";
    }

    @GetMapping("/{groupId}/leave")
    public String userLeaveGroup(@PathVariable("userId") String userIdString, @PathVariable("gameId") String gameIdString, @PathVariable("groupId") String groupIdString, Model model) {
        int userId = Integer.parseInt(userIdString);
        int groupId = Integer.parseInt(groupIdString);

        if (userId == USERNOTLOGGEDIN) {
            String redirectionUrl = requestString(userIdString, gameIdString) + "/" + groupIdString + "/join";
            model.addAttribute("redirectionUrl", redirectionUrl);
            return "user-account-login";
        } else if (groupListingService.userIsOwner(userId, groupId)) {
            model.addAttribute("errorMessage", new GroupListingError(GroupListingError.ErrorType.LEAVE_OWNER));
            return "redirect:" + requestString(userIdString, gameIdString) + "/";
        } else {
            groupListingService.removeMember(userId, groupId);
            GroupListing groupListing = groupListingService.getGroupListingById(groupId);
            groupListing.memberLeaves();
            groupListingService.updateGroupListing(groupListing);
            model.addAttribute("successMessage", new GroupListingSuccess(GroupListingSuccess.SuccessType.LEAVE));
            return "redirect:" + requestString(userIdString, gameIdString) + "/";
        }
    }

    @GetMapping({"/create", "/create/"})
    public String newGroupCreation(@PathVariable("userId") String userIdString, @PathVariable("gameId") String gameIdString, Model model) {
        int userId = Integer.parseInt(userIdString);
        int gameId = Integer.parseInt(gameIdString);
        String redirectionUrl = requestString(userIdString, gameIdString) + "/create";

        if (userId == USERNOTLOGGEDIN) {
            model.addAttribute("redirectionUrl", redirectionUrl);
            return "user-account-login";
        } else {
            if (tagService != null)
                model.addAttribute("tagList", tagService.getAllTagsForGame(gameId));
            else
                model.addAttribute("tagList", null);
            model.addAttribute("groupListing", new GroupListing());
            model.addAttribute("tag", new Tag());
            return "new-group-listing";
        }
    }

    @PostMapping(value = {"/create/new", "/new"}, consumes = "application/x-www-form-urlencoded")
    public String createNewGroup(@PathVariable("userId") String userIdString, @PathVariable("gameId") String gameIdString, GroupListing groupListing, Model model) {
        int gameId = Integer.parseInt(gameIdString);
        int userId = Integer.parseInt(userIdString);

        groupListing.setlistingId(gameListingService.getGameListingById(gameId));
        groupListing.setOwnerId(userService.getUserById(userId).get());
        groupListing.setListingPostDate(new Date());
        groupListing.setOpenMemberSpots(groupListing.getMaxNumMembers() - 1);
        groupListingService.addNewGroupListing(groupListing);

        model.addAttribute("successMessage", new GroupListingSuccess(GroupListingSuccess.SuccessType.CREATE));
        return "redirect:" + requestString(userIdString, gameIdString) + "/";
    }

}
