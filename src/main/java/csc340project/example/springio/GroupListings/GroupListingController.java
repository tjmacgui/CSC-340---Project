package csc340project.example.springio.GroupListings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game/{gameId}/groups")
public class GroupListingController {
    @Autowired
    GroupListingService groupListingService;

    @GetMapping({"/", "", "view"})
    public String viewAllGroupListings(@PathVariable("gameId") String gameIdString, Model model) {
        int gameId = Integer.parseInt(gameIdString);
        model.addAttribute("groupListings", groupListingService.getAllGroupListingsForGame(gameId));
        return "group-listings";
    }

    @GetMapping("/search/{searchInput}")
    public String sortGroupListingsBySearch(@PathVariable("gameId") String gameIdString, @PathVariable("searchInput") String searchInput, Model model) {
        int gameId = Integer.parseInt(gameIdString);
        model.addAttribute("groupListings", groupListingService.getGroupsBySearch(searchInput, gameId));
        return "group-listings";
    }


}
