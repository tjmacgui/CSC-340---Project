package csc340project.example.springio.admin;

import csc340project.example.springio.GameListings.Listing;
import csc340project.example.springio.GameListings.ListingService;
import csc340project.example.springio.GroupListings.GroupListingService;
import csc340project.example.springio.User.User;
import csc340project.example.springio.User.UserReport.UserReportService;
import csc340project.example.springio.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private ListingService listingService;
    @Autowired
    private UserReportService userReportService;
    @Autowired
    private GroupListingService groupListingService;

    //overall homepage
    @GetMapping("/home")
    public String adminLogin() {
        return "index";
    }

    @GetMapping("/userLogin")
    public String userLogin() {
        return "/User Pages/user-account-login";
    }

    //admin login page
    @GetMapping("/login")
    public String loginpage() {
        return "/Admin Pages/admin-login";
    }

    //admin dashboard
    @GetMapping("/dashboard")
    public String dashbaord() {
        return "/Admin Pages/admin-interface";
    }

    // listing stuff
    @GetMapping("/listings")
    public String getAllListings(Model model) {
        model.addAttribute("listingList", listingService.getAllListings());
        return "/Admin Pages/listing-page";
    }

    @GetMapping("/createlisting")
    public String creationPage() {
        return "/Admin Pages/create-listing";
    }

    @PostMapping("/createlisting")
    public String createListing(Listing listing) {
        listingService.saveListing(listing);
        return "redirect:/games/";
    }

    @GetMapping("/listings/delete/{id}")
    public String deleteListing(@PathVariable Integer id) {
        listingService.deleteListing(id);
        return "/Admin Pages/listing-page";
    }

    //user stuff

    //view all users in table
    @GetMapping("/allUsers")
    public String viewUsers(Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        return ("/Admin Pages/view-users");
    }

    //troubleshooting user profile
    @PostMapping("/user-update")
    public String updateUser(User user){
        userService.saveEdit(user);
        return "redirect:/admin/allUsers";
    }
    @GetMapping("/user-update/{id}")
    public String updateUsers(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.userId(id));
        return "/Admin Pages/update-user";
    }

    //view specific user profule
    @GetMapping("/user/{userId}")
    public String getUser(@PathVariable int userId, Model model) {
        model.addAttribute("user", userService.userId(userId));
        return "/Admin Pages/user-profile";
    }

    //delete user account
    @GetMapping("/user-delete/{id}")
    public String deleteGoalById(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/admin/allUsers";
    }

    //view all flagged users
    @GetMapping("/users/flagged")
    public String seeFlaggedUsers(Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        return "/Admin Pages/flagged-users";
    }

    // search users

    @GetMapping("/user/search")
    public String searchUser(@RequestParam("username") String username, Model model) {
        if ("all".equals(username)) {
            model.addAttribute("userList", userService.getAllUsers());
            return "/Admin Pages/view-users";
        } else {
            User userList = userService.findByUsername(username);
            model.addAttribute("userList", userList);
            return "/Admin Pages/view-users";
        }
    }

    //unflag a user
    @GetMapping("users/unflag/{userId}")
    public String unflagUsers(@PathVariable int userId) {
        userService.unflagUser(userId);
        return "redirect:/admin/user/"+userId;
    }
    @GetMapping("users/flag/{userId}")
    public String flagUsers(@PathVariable int userId) {
        userService.flagUser(userId);
        return "redirect:/admin/user/"+userId;
    }

    //groups
    @GetMapping("/groups")
    public String getAllGroups(Model model) {
        model.addAttribute("groupList", groupListingService.getAllGroups());
        return "/Admin Pages/view-groups";
    }


    //messaging
    @GetMapping("/messages")
    public String messagingHub() {
        return "/Admin Pages/messages";

    }

    //report messages
    @GetMapping("/user/reports")
    public String userReports(Model model) {
        model.addAttribute("userReportList", userReportService.getAllUserReports());
        return "/Admin Pages/user-reports";
    }
}
