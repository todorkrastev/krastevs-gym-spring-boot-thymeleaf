package com.todorkrastev.krastevsgym.web;

import com.todorkrastev.krastevsgym.model.dto.ExerciseShortInfoDTO;
import com.todorkrastev.krastevsgym.service.ExerciseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ExerciseController {

    private ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/exercises")
    public String exercises(Model model) {
        List<ExerciseShortInfoDTO> exercises = this.exerciseService.getAll();

        model.addAttribute("exercises", exercises);

        return "exercises";
    }
}
