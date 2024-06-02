package csc340project.example.springio.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllUsers() {
        List<User> users = userService.getAllUsers();
        StringBuilder response = new StringBuilder();
        for (User user : users) {
            response.append(user.getUserId()).append(", ")
                    .append(user.getUsername()).append(", ")
                    .append(user.getPassword()).append(", ")
                    .append(user.getProfileImageId()).append(", ")
                    .append(user.getProfileCreationDate()).append(", ")
                    .append(user.getIsFlagged()).append("\n");
        }
        return response.toString();
    }

    @PostMapping
    public String createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return "User created with ID: " + savedUser.getUserId();
    }

    @GetMapping("/{userId}")
    public String getUserById(@PathVariable Integer userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.map(value -> value.getUserId() + ", " +
                value.getUsername() + ", " +
                value.getPassword() + ", " +
                value.getProfileImageId() + ", " +
                value.getProfileCreationDate() + ", " +
                value.getIsFlagged()).orElse("User not found");
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return "User with ID " + userId + " deleted";
    }

}
