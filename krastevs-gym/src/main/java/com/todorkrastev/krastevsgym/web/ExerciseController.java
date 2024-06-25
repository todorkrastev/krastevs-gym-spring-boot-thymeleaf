package com.todorkrastev.krastevsgym.web;

import com.todorkrastev.krastevsgym.model.dto.CreateExerciseDTO;
import com.todorkrastev.krastevsgym.model.dto.ExerciseShortInfoDTO;
import com.todorkrastev.krastevsgym.model.enums.EquipmentTypeEnum;
import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;
import com.todorkrastev.krastevsgym.service.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/exercises")
public class ExerciseController {

    private ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @ModelAttribute("allExerciseCategories")
    public ExerciseCategoryEnum[] allExerciseCategories() {
        return ExerciseCategoryEnum.values();
    }

    @ModelAttribute("allEquipmentTypes")
    public EquipmentTypeEnum[] allEquipmentTypes() {
        return EquipmentTypeEnum.values();
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

        return "exercise-create";
    }

    @PostMapping("/create")
    public String createExercise(@Valid CreateExerciseDTO createExerciseDTO,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createExerciseDTO", createExerciseDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createExerciseDTO", bindingResult);

            return "redirect:/exercises/create";
        }

        long newExerciseId = exerciseService.createExercise(createExerciseDTO);

        return "redirect:/exercises/" + newExerciseId;
    }

}
