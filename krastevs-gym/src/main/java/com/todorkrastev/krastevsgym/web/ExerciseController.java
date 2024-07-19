package com.todorkrastev.krastevsgym.web;

import com.todorkrastev.krastevsgym.model.dto.CreateExerciseDTO;
import com.todorkrastev.krastevsgym.model.dto.CreateExerciseNotesDTO;
import com.todorkrastev.krastevsgym.model.enums.EquipmentTypeEnum;
import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;
import com.todorkrastev.krastevsgym.model.user.KrastevsGymUserDetails;
import com.todorkrastev.krastevsgym.service.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping("/{id}")
    public String exerciseDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("exerciseDetails", exerciseService.getExerciseDetails(id));

        return "exercise";
    }

    @PostMapping("/{id}")
    public String createExerciseNotes(@PathVariable("id") Long id,
                                      @Valid CreateExerciseNotesDTO createExerciseNotesDTO
    ) {
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
            model.addAttribute("createExerciseDTO", new CreateExerciseDTO());
        }

        return "exercise-create";
    }

    @PostMapping("/create")
    public String createExercise(@Valid CreateExerciseDTO createExerciseDTO,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails instanceof KrastevsGymUserDetails krastevsGymUserDetails) {
            Long currentUserId = krastevsGymUserDetails.getCurrId();
            createExerciseDTO.setCurrUserId(currentUserId);
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createExerciseDTO", createExerciseDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createExerciseDTO", bindingResult);

            return "redirect:/exercises/create";
        }

        Long newExerciseId = exerciseService.createExercise(createExerciseDTO);
        return "redirect:/exercises/" + newExerciseId;
    }
}
