package csc340project.example.springio.GroupListings;

import csc340project.example.springio.GameListings.ListingService;
import csc340project.example.springio.GroupMember.GroupMemberService;
import csc340project.example.springio.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

/*todo: import GameListingService when complete*/

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
    GroupMemberService groupMemberService;

    private String requestString(String userId, String gameId) {
        return "/user/" + userId + "/game/" + gameId + "groups";
    }

    @GetMapping({"/", "", "/view"})
    public String viewAllGroupListings(@PathVariable("gameId") String gameIdString, Model model) {
        int gameId = Integer.parseInt(gameIdString);
        model.addAttribute("game", gameListingService.getGameListingById(gameId));
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
                return requestString(userIdString, gameIdString) + "/";
            } else if (groupListingService.userInGroup(userId, groupId)) {
                model.addAttribute("errorMessage", new GroupListingError(GroupListingError.ErrorType.JOIN_INGROUP));    //user is already in the group
                return requestString(userIdString, gameIdString) + "/";
            } else {
                model.addAttribute("successMessage", new GroupListingSuccess(GroupListingSuccess.SuccessType.JOIN));    //user successfully joins the group
                groupListingService.addNewMember(groupId, userId);
                selectedGroupListing.memberJoin();
                return "redirect:" + requestString(userIdString, gameIdString) + "/";
            }
        }
    }

    @PostMapping("/{groupId}/update")
    public String updateGroup(@PathVariable("userId") String userIdString, @PathVariable("gameId") String gameIdString, @PathVariable("groupId") String groupIdString, @RequestBody GroupListing groupListing, Model model) {
        int gameId = Integer.parseInt(gameIdString);
        int userId = Integer.parseInt(userIdString);
        int groupId = Integer.parseInt(groupIdString);

        groupListing.setGameId(gameListingService.getGameListingById(gameId));
        groupListing.setOwnerId(userService.getUserById(userId).get());
        groupListing.setGroupListingId(groupId);

        model.addAttribute("successMessage", new GroupListingSuccess(GroupListingSuccess.SuccessType.UPDATE));
        return "redirect:" + requestString(userIdString, gameIdString) + "/";
    }

    @PostMapping("/create")
    public String createNewGroup(@PathVariable("userId") String userIdString, @PathVariable("gameId") String gameIdString, @RequestBody GroupListing groupListing, Model model) {
        int gameId = Integer.parseInt(gameIdString);
        int userId = Integer.parseInt(userIdString);

        groupListing.setGameId(gameListingService.getGameListingById(gameId));
        groupListing.setOwnerId(userService.getUserById(userId).get());
        groupListing.setListingPostDate(new Date());

        model.addAttribute("successMessage", new GroupListingSuccess(GroupListingSuccess.SuccessType.CREATE));
        return "redirect:" + requestString(userIdString, gameIdString) + "/";
    }

    @GetMapping("/{groupId}/leave")
    public String userLeaveGroup(@PathVariable("userId") String userIdString, @PathVariable("gameId") String gameIdString, @PathVariable("groupId") String groupIdString, Model model) {
        int gameId = Integer.parseInt(gameIdString);
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
            groupListingService.getGroupListingById(groupId).memberLeaves();
            model.addAttribute("successMessage", new GroupListingSuccess(GroupListingSuccess.SuccessType.LEAVE));
            return "redirect:" + requestString(userIdString, gameIdString) + "/";
        }
    }
}
