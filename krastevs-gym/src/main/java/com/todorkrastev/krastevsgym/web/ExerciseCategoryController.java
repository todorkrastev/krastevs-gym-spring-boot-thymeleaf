package com.todorkrastev.krastevsgym.web;

import com.todorkrastev.krastevsgym.model.dto.ExerciseCategoryInfoDTO;
import com.todorkrastev.krastevsgym.model.dto.ExerciseShortInfoDTO;
import com.todorkrastev.krastevsgym.service.ExerciseCategoryService;
import com.todorkrastev.krastevsgym.service.ExerciseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/exercises")
public class ExerciseCategoryController {

    private final ExerciseCategoryService exerciseCategoryService;
    private final ExerciseService exerciseService;

    public ExerciseCategoryController(ExerciseCategoryService exerciseCategoryService, ExerciseService exerciseService) {
        this.exerciseCategoryService = exerciseCategoryService;
        this.exerciseService = exerciseService;
    }

    @GetMapping("/categories")
    public String allCategories(Model model) {
        List<ExerciseCategoryInfoDTO> categories = exerciseCategoryService.getAllCategories();

        model.addAttribute("categories", categories);

        return "exercises";
    }

    @GetMapping("/categories/{id}")
    public String exercisesByCategoryId(@PathVariable("id") Long id, Model model) {
        ExerciseCategoryInfoDTO exerciseCategoryInfoDTO = exerciseCategoryService.findById(id);
        model.addAttribute("exerciseCategoryInfo", exerciseCategoryInfoDTO);

        List<ExerciseShortInfoDTO> exercisesByCategory = exerciseService.getExercisesByGivenCategory(exerciseCategoryInfoDTO.exerciseCategory());
        model.addAttribute("exercisesByCategory", exercisesByCategory);

        return "exercises-by-category";
    }
}
