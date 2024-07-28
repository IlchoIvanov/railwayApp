package com.example.railwayapp.web;

import com.example.railwayapp.model.user.RailwayAppUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(@AuthenticationPrincipal RailwayAppUserDetails userDetails, Model model) {
        if(userDetails == null) {
            return "index";
        }

        model.addAttribute("username", userDetails.getName());
        return "index";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }
    @GetMapping("/contacts")
    public String contacts(){
        return "contacts";
    }


}
