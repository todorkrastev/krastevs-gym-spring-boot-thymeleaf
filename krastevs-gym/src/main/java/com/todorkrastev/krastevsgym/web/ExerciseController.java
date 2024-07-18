package com.todorkrastev.krastevsgym.web;

import com.todorkrastev.krastevsgym.model.dto.CreateExerciseDTO;
import com.todorkrastev.krastevsgym.model.dto.CreateExerciseNotesDTO;
import com.todorkrastev.krastevsgym.model.enums.EquipmentTypeEnum;
import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;
import com.todorkrastev.krastevsgym.service.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;

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

    @GetMapping("/all")
    public String getAllOffers(Model model) {
        model.addAttribute("allOffers", exerciseService.getAllExercises());
        //TODO: This is just a demo. Delete after implementing it
        return "bla-bla";
    }

    @GetMapping("/{id}")
    public String exerciseDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("exerciseDetails", exerciseService.getExerciseDetails(id));

        return "exercise";
    }

    @PostMapping("/{id}")
    public String createExerciseNotes(@PathVariable("id") Long id,
                                      @Valid CreateExerciseNotesDTO createExerciseNotesDTO) {
        exerciseService.createExerciseNotes(createExerciseNotesDTO, id);

        return "redirect:/exercises/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteExercise(@PathVariable("id") Long id) {
        exerciseService.deleteExercise(id);

        return "redirect:/exercises/categories";
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

        System.out.println(createExerciseDTO);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createExerciseDTO", createExerciseDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createExerciseDTO", bindingResult);

            return "redirect:/exercises/create";
        }
        //Thymeleaf
        //long newExerciseId = exerciseService.createExercise(createExerciseDTO);
        // return "redirect:/exercises/" + newExerciseId;

        //REST
        exerciseService.createExercise(createExerciseDTO);

        return "redirect:/exercises/categories";
    }
}
