package csc340project.example.springio.User.UserRating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/userRatings")
public class UserRatingController {

    @Autowired
    private UserRatingService userRatingService;

    @GetMapping("/")
    public ResponseEntity<List<UserRating>> getAllUserRatings() {
        List<UserRating> userRatings = userRatingService.getAllUserRatings();
        return ResponseEntity.ok(userRatings);
    }

    @PostMapping("/")
    public ResponseEntity<UserRating> createUserRating(@RequestBody UserRating userRating) {
        UserRating savedUserRating = userRatingService.saveUserRating(userRating);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserRating);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRating> getUserRatingById(@PathVariable Integer id) {
        Optional<UserRating> userRating = userRatingService.getUserRatingById(id);
        return userRating.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserRating(@PathVariable Integer id) {
        userRatingService.deleteUserRating(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
