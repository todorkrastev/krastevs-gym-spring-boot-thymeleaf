package com.todorkrastev.krastevsgym.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserLoginController {

    @GetMapping("/users/login")
    public String login() {
        //TODO: code the login page
        return "auth-login";
    }
}
