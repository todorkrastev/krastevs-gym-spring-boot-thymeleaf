package com.todorkrastev.krastevsgym.web;

import com.todorkrastev.krastevsgym.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class LogoutController {

    private final UserService userService;

    public LogoutController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/logout")
    public String logout() {
        userService.logout();

        return "redirect:/";
    }
}
