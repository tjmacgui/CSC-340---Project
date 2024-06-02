package csc340project.example.springio.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
//@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/home")
    public String adminLogin() {
        return "index";
    }

    @GetMapping("/idk")
    public String homePage() {
        return "/User Pages/user-account-login";
    }

    @GetMapping("admin/dashboard")
    public String dashbaord(){
        return "/User Pages/admin-interface";
    }
    @GetMapping("/admin/login")
    public String login() {
        return "/User Pages/admin-login";
    }
}
