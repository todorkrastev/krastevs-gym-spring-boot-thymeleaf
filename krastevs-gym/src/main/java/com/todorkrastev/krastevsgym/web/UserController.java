package com.todorkrastev.krastevsgym.web;

import com.todorkrastev.krastevsgym.model.dto.UserRegisterDTO;
import com.todorkrastev.krastevsgym.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(UserRegisterDTO userRegisterDTO) {
        this.userService.registerUser(userRegisterDTO);

        return "index";
    }
}
