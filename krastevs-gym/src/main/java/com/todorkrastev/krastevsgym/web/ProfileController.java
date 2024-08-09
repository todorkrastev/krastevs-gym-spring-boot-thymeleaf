package com.todorkrastev.krastevsgym.web;

import com.todorkrastev.krastevsgym.model.dto.UserInfoDTO;
import com.todorkrastev.krastevsgym.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getProfile(Model model) {
        UserInfoDTO userInfoDTO = userService.getProfile();
        model.addAttribute("userInfoDTO", userInfoDTO);

        return "profile";
    }

    @GetMapping("/edit")
    public String editProfile(Model model) {
        UserInfoDTO userInfoDTO = userService.getProfile();
        model.addAttribute("userInfoDTO", userInfoDTO);

        return "profile-edit";
    }

    @PutMapping("/edit")
    public String editProfile(@Valid UserInfoDTO userInfoDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userInfoDTO", userInfoDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userInfoDTO", bindingResult);

            return "redirect:/profile/edit/error";
        }

        boolean hasEmailChanged = userService.editProfile(userInfoDTO);

        if (hasEmailChanged) {
            request.getSession().invalidate();
            return "redirect:/login";
        } else {
            return "redirect:/profile";
        }
    }

    @GetMapping("/edit/error")
    public String editProfileError(Model model) {
        if (!model.containsAttribute("userInfoDTO")) {
            return "redirect:/profile/edit";
        }

        return "profile-edit";
    }
}