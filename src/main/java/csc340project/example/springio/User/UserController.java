package csc340project.example.springio.User;

import csc340project.example.springio.GameListings.Listing;
import csc340project.example.springio.GameListings.ListingRepo;
import csc340project.example.springio.User.OwnedGame.OwnedGame;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ListingRepo listingRepo;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/login")
    public String loginPage(Model model) {
        return "User Pages/user-account-login";
    }

    @PostMapping("/users/login")
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

    @GetMapping("/users/account")
    public String getUserAccount(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            //finds User from username
            User user = userService.findByUsername(username);
            //adds user.get to model
            model.addAttribute("username", user.getUsername());
            model.addAttribute("leaderRanking", user.getLeaderRanking());
            model.addAttribute("thumbsUp", user.getThumbsUp());
        }
        return "User Pages/user-account";
    }

    @GetMapping("/users/signup")
    public String signupPage(Model model) {
        model.addAttribute("user", new User());
        return "User Pages/user-account-signup";
    }

    @PostMapping("/users/signup")
    public String signupUser(@ModelAttribute User user, Model model) {
        if (userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "Username already exists");
            return "User Pages/user-account-signup";
        }
        userService.saveUser(user);
        return "redirect:/users/account";
    }

    @GetMapping("/index")
    public String getIndexPage(Model model) {
        // get the logged in user from the security context
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(loggedInUsername).orElseThrow(() -> new RuntimeException("User not found"));

        // get the owned games for the user
        List<OwnedGame> ownedGames = user.getOwnedGames();

        // get listing ids from owned games
        List<Integer> listingIds = ownedGames.stream()
                .map(OwnedGame::getListing)// get listing obj from each owned game
                .map(Listing::getListingId)// get listingid from each listing
                .collect(Collectors.toList()); // list of listingid

        // get listings by ids
        List<Listing> listings = listingRepo.findAllById(listingIds);

        // add listings to the model
        model.addAttribute("listings", listings);
        model.addAttribute("ownedGames", ownedGames);

        return "index";
    }

    @GetMapping("/users/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // gets auth information
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //if not null user is logged in
        if (auth != null) {
            //uses built in logout http: request/response , auth = current user
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/users/login?logout=true";
    }

    @PostMapping("/users/steamlink")
    public String linkSteamAccount(@RequestParam("steamUsername") String steamUsername, Model model) throws Exception {
        //get the logged in user from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        //loads all steam games in the background with async. otherwise the page just hangs.
        userService.linkSteamAccount(username, steamUsername);

        model.addAttribute("message", "Games will be imported in the background.");
        return "User Pages/user-account"; // Redirect to user account page
    }

}
