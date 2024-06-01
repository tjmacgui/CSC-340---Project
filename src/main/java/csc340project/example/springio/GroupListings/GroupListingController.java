package csc340project.example.springio.GroupListings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/{userId}/game/{gameId}/groups")
public class GroupListingController {
    private static final int USERNOTLOGGEDIN = 0;

    @Autowired
    GroupListingService groupListingService;

    @Autowired
    GameListingService gameListingService;

    private String requestString(String userId, String gameId) {
        return "/user/" + userId + "/game/" + gameId + "groups";
    }

    @GetMapping({"/", "", "view"})
    public String viewAllGroupListings(@PathVariable("gameId") String gameIdString, Model model) {
        int gameId = Integer.parseInt(gameIdString);
        model.addAttribute("game", gameListingService.getGameLisingById(gameId));
        model.addAttribute("groupListings", groupListingService.getAllGroupListingsForGame(gameId));
        return "group-listings";
    }

    @GetMapping("/search/{searchInput}")
    public String sortGroupListingsBySearch(@PathVariable("gameId") String gameIdString, @PathVariable("searchInput") String searchInput, Model model) {
        int gameId = Integer.parseInt(gameIdString);
        model.addAttribute("game", gameListingService.getGameLisingById(gameId));
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

    

}
