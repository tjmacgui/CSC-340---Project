package csc340project.example.springio.GameTags;

import csc340project.example.springio.GameListings.ListingService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/{userId}/game/{gameId}")
public class TagController {
    @Autowired
    TagService tagService;

    @Autowired
    ListingService listingService;

    private String redirectionURL(String userIdString, String gameIdString) {
        int userId = Integer.parseInt(userIdString);
        int gameId = Integer.parseInt(gameIdString);
        return "/user/" + userId + "/game/" + gameId;
    }

    @PostMapping(value = "/newtag", consumes = "application/x-www-form-urlencoded")
    public String postNewTagForGame(@PathVariable("userId") String userIdString, @PathVariable("gameId") String gameIdString, Tag tag, Model model) {
        int gameId = Integer.parseInt(gameIdString);

        tag.setListing(listingService.getGameListingById(gameId));
        tagService.saveNewTag(tag);

        return "redirect:" + redirectionURL(userIdString, gameIdString) + "/";
    }
}
