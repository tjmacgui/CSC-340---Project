package csc340project.example.springio.User.UserRating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/userRatings")
public class UserRatingController {
    @Autowired
    private UserRatingService userRatingService;

    @GetMapping
    public String getAllUserRatings() {
        List<UserRating> userRatings = userRatingService.getAllUserRatings();
        StringBuilder response = new StringBuilder();
        for (UserRating userRating : userRatings) {
            response.append(userRating.getId()).append(", ")
                    .append(userRating.getRating()).append(", ")
                    .append(userRating.getUser().getUserId()).append("\n");
        }
        return response.toString();
    }

    @PostMapping
    public String createUserRating(@RequestBody UserRating userRating) {
        UserRating savedUserRating = userRatingService.saveUserRating(userRating);
        return "UserRating created with ID: " + savedUserRating.getId();
    }

    @GetMapping("/{id}")
    public String getUserRatingById(@PathVariable Integer id) {
        Optional<UserRating> userRating = userRatingService.getUserRatingById(id);
        return userRating.map(value -> value.getId() + ", " +
                value.getRating() + ", " +
                value.getUser().getUserId()).orElse("UserRating not found");
    }

    @DeleteMapping("/{id}")
    public String deleteUserRating(@PathVariable Integer id) {
        userRatingService.deleteUserRating(id);
        return "UserRating with ID " + id + " deleted";
    }

}
