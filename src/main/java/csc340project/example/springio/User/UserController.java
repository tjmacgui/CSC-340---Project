package csc340project.example.springio.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "User Pages/user-account-login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute LoginRequest loginRequest, Model model) {
        boolean isAuthenticated = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        if (isAuthenticated) {
            User user = userService.findByUsername(loginRequest.getUsername());
            model.addAttribute("username", user.getUsername());
            model.addAttribute("leaderRanking", user.getLeaderRanking());
            model.addAttribute("thumbsUp", user.getThumbsUp());
            return "User Pages/user-account";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "User Pages/user-account-login";
        }
    }

    @GetMapping("/account")
    public String getUserAccount(Model model) {
        // Assuming the user is already authenticated and their data is in the session or context
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            User user = userService.findByUsername(username);
            model.addAttribute("username", user.getUsername());
            model.addAttribute("leaderRanking", user.getLeaderRanking());
            model.addAttribute("thumbsUp", user.getThumbsUp());
        }
        return "User Pages/user-account";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        return "User Pages/user-account-signup";
    }

    @PostMapping("/signup")
    public String signupUser(@ModelAttribute User user, Model model) {
        if (userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "Username already exists");
            return "User Pages/user-account-signup";
        }
        userService.saveUser(user);
        return "redirect:/users/account";
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<User> getUserProfile(@PathVariable Integer userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/profile/{userId}")
    public ResponseEntity<User> updateUserProfile(@PathVariable Integer userId, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(userId, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/profile/{userId}")
    public ResponseEntity<Void> deleteUserProfile(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
