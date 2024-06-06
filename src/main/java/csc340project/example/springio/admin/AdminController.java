package csc340project.example.springio.admin;

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

    @GetMapping("/home")
    public String adminLogin() {
        return "index";
    }

    @GetMapping("/login")
    public  String loginpage(){
        return "/Admin Pages/admin-login";
    }
    @GetMapping("/idk")
    public String homePage() {
        return "/User Pages/user-account-login";
    }

    @GetMapping("/dashboard")
    public String dashbaord(){
        return "/Admin Pages/admin-interface";
    }


    //user stuff

    @GetMapping("/allUsers")
    public String viewUsers(Model model){
        model.addAttribute("userList", userService.getAllUsers());
        return ("/Admin Pages/view-users");
    }

    @GetMapping("/update/user/{id}")
    public String updateUser(@PathVariable int id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "/Admin Pages/update-user";
    }
    @GetMapping("/user/{userId}")
    public String getUser(@PathVariable int userId, Model model){
        model.addAttribute("user", userService.getUserById(userId));
        return "/Admin Pages/user-profile";
    }
    @GetMapping("delete/{id}")
    public String deleteGoalById(@PathVariable int id){
        userService.deleteUser(id);
        return "redirect:/admin/allUsers";
    }

}
