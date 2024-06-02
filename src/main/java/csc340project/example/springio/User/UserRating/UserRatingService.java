package csc340project.example.springio.User.UserRating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRatingService {
    @Autowired
    private UserRatingRepository userRatingRepository;

    public List<UserRating> getAllUserRatings() {
        return userRatingRepository.findAll();
    }

    public UserRating saveUserRating(UserRating userRating) {
        return userRatingRepository.save(userRating);
    }

    public Optional<UserRating> getUserRatingById(Integer id) {
        return userRatingRepository.findById(id);
    }

    public void deleteUserRating(Integer id) {
        userRatingRepository.deleteById(id);
    }

}
