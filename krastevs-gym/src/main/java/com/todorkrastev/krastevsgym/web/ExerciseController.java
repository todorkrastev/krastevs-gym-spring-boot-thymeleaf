package com.todorkrastev.krastevsgym.web;

import com.todorkrastev.krastevsgym.model.dto.CreateExerciseDTO;
import com.todorkrastev.krastevsgym.model.dto.ExerciseShortInfoDTO;
import com.todorkrastev.krastevsgym.model.enums.EquipmentTypeEnum;
import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;
import com.todorkrastev.krastevsgym.service.ExerciseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/exercises")
public class ExerciseController {

    private ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }


    @GetMapping()
    public String exercises(Model model) {
        List<ExerciseShortInfoDTO> exercises = this.exerciseService.getAll();

        model.addAttribute("exercises", exercises);

        return "exercises";
    }

    @GetMapping("/create")
    public String createExercise(Model model) {
        if (!model.containsAttribute("createExerciseDTO")) {
            model.addAttribute("createExerciseDTO", CreateExerciseDTO.empty());
        }
        model.addAttribute("allExerciseCategories", ExerciseCategoryEnum.values());
        model.addAttribute("allEquipmentTypes", EquipmentTypeEnum.values());
        return "exercise-create";
    }

    @PostMapping("/create")
    public String createExercise(CreateExerciseDTO createExerciseDTO) {
        exerciseService.createExercise(createExerciseDTO);

        return "exercise-create";
    }

}
