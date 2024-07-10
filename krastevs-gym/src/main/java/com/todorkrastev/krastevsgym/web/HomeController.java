package com.todorkrastev.krastevsgym.web;

import com.todorkrastev.krastevsgym.model.user.KrastevsGymUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails instanceof KrastevsGymUserDetails krastevsGymUserDetails) {
            model.addAttribute("welcomeMessage", krastevsGymUserDetails.getFullName());
        } else {
            model.addAttribute("welcomeMessage", "Anonymous");
        }
        return "index";
    }
}
