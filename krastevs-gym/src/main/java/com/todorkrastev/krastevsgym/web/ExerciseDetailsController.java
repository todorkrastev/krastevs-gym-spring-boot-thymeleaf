package com.todorkrastev.krastevsgym.web;

import com.todorkrastev.krastevsgym.service.ExerciseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exercises")
public class ExerciseDetailsController {

    private final ExerciseService exerciseService;

    public ExerciseDetailsController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/{id}")
    public String exerciseDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("exerciseDetails", exerciseService.getExerciseDetails(id));

        return "exercise";
    }
}
