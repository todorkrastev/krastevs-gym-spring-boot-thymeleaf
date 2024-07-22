package com.todorkrastev.krastevsgym.web;

import com.todorkrastev.krastevsgym.model.dto.ExerciseCategoryInfoDTO;
import com.todorkrastev.krastevsgym.model.dto.ExerciseShortInfoDTO;
import com.todorkrastev.krastevsgym.model.user.KrastevsGymUserDetails;
import com.todorkrastev.krastevsgym.service.ExerciseCategoryService;
import com.todorkrastev.krastevsgym.service.ExerciseService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/exercises/exercises-by-category")
public class ExerciseCategoryController {
    private final ExerciseCategoryService exerciseCategoryService;
    private final ExerciseService exerciseService;

    public ExerciseCategoryController(ExerciseCategoryService exerciseCategoryService, ExerciseService exerciseService) {
        this.exerciseCategoryService = exerciseCategoryService;
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public String allCategories(Model model) {
        List<ExerciseCategoryInfoDTO> categories = exerciseCategoryService.getAllCategories();

        model.addAttribute("categories", categories);

        return "exercises";
    }

    @GetMapping("/{id}")
    public String exercisesByCategoryId(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails instanceof KrastevsGymUserDetails krastevsGymUserDetails) {
            Long userId = krastevsGymUserDetails.getCurrId();

            List<ExerciseShortInfoDTO> allExercisesByCategory = exerciseService.getExercisesByCategoryIdAndUserId(id, userId);
            model.addAttribute("exercisesByCategory", allExercisesByCategory);
        }


        List<ExerciseCategoryInfoDTO> categories = exerciseCategoryService.getAllCategories();
        model.addAttribute("categories", categories);

        return "exercises-by-category";
    }
}
