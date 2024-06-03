package csc340project.example.springio.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping({"", "/", "/home", "/index"})
    public String home(){
        return "redirect:/games/view";
    }
}
