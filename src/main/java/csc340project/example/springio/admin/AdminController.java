package csc340project.example.springio.admin;

import csc340project.example.springio.GameListings.Listing;
import csc340project.example.springio.GameListings.ListingService;
import csc340project.example.springio.User.User;
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

    //overall homepage
    @GetMapping("/home")
    public String adminLogin() {
        return "index";
    }

    //admin login page
    @GetMapping("/login")
    public  String loginpage(){
        return "/Admin Pages/admin-login";
    }

    //admin dashboard
    @GetMapping("/dashboard")
    public String dashbaord(){
        return "/Admin Pages/admin-interface";
    }

    // listing stuff
    @GetMapping("/listings")
    public String getAllListings(Model model) {
        model.addAttribute("listingList",listingService.getAllListings());
        return "/Admin Pages/listing-page";
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
    public String viewUsers(Model model){
        model.addAttribute("userList", userService.getAllUsers());
        return ("/Admin Pages/view-users");
    }

    //troubleshooting user profile
    @GetMapping("/user-update/{id}")
    public String updateUser(@PathVariable int id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "/Admin Pages/update-user";
    }

    //view specific user profule
    @GetMapping("/user/{userId}")
    public String getUser(@PathVariable int userId, Model model){
        model.addAttribute("user", userService.getUserById(userId));
        return "/Admin Pages/user-profile";
    }

    //delete user account
    @GetMapping("/user-delete/{id}")
    public String deleteGoalById(@PathVariable int id){
        userService.deleteUser(id);
        return "redirect:/admin/allUsers";
    }

}
