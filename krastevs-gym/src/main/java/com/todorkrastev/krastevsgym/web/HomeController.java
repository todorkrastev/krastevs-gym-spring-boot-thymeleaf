package com.todorkrastev.krastevsgym.web;

import com.todorkrastev.krastevsgym.model.dto.ActivityDTO;
import com.todorkrastev.krastevsgym.model.dto.HeroImageDTO;
import com.todorkrastev.krastevsgym.model.enums.HeroImageCategoryEnum;
import com.todorkrastev.krastevsgym.model.user.KrastevsGymUserDetails;
import com.todorkrastev.krastevsgym.service.ActivityService;
import com.todorkrastev.krastevsgym.service.HeroImageService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final ActivityService activityService;
    private final HeroImageService heroImageService;

    public HomeController(ActivityService activityService, HeroImageService heroImageService) {
        this.activityService = activityService;
        this.heroImageService = heroImageService;
    }

    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails instanceof KrastevsGymUserDetails krastevsGymUserDetails) {
            model.addAttribute("welcomeMessage", krastevsGymUserDetails.getFullName());
        } else {
            model.addAttribute("welcomeMessage", "Anonymous");
        }

        List<ActivityDTO> activities = activityService.findAll();
        model.addAttribute("activities", activities);

        HeroImageDTO heroImage = heroImageService.getHeroPageByGivenCategory(HeroImageCategoryEnum.HOME);
        model.addAttribute("heroImage", heroImage);

        return "index";
    }
}
