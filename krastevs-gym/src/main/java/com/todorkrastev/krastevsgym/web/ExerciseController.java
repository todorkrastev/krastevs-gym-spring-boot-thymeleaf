package com.todorkrastev.krastevsgym.web;

import com.todorkrastev.krastevsgym.model.dto.CreateExerciseDTO;
import com.todorkrastev.krastevsgym.model.dto.CreateExerciseNotesDTO;
import com.todorkrastev.krastevsgym.model.dto.EditExerciseDTO;
import com.todorkrastev.krastevsgym.model.dto.ExerciseDetailsDTO;
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
@RequestMapping("/exercises/exercises-by-category/exercise")
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
    public String exerciseDetails(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails instanceof KrastevsGymUserDetails krastevsGymUserDetails) {
            Long currentUserId = krastevsGymUserDetails.getCurrId();

            ExerciseDetailsDTO exerciseDetailsDTO = exerciseService.getExerciseDetails(id, currentUserId);
            model.addAttribute("exerciseDetails", exerciseDetailsDTO);

            if (exerciseDetailsDTO.getCreatorId().equals(currentUserId)) {
                model.addAttribute("isCreator", true);
            } else {
                model.addAttribute("isCreator", false);
            }
        }

        return "exercise";
    }

    //TODO: implement the logic in the exercise.html
    @PutMapping("/{id}")
    public String editExercise(@PathVariable("id") Long id, @Valid EditExerciseDTO editExerciseDTO, Model model) {
        ExerciseDetailsDTO exerciseDetailsDTO = exerciseService.editExercise(id, editExerciseDTO);
        model.addAttribute("exerciseDetails", exerciseDetailsDTO);

        return "redirect:/exercises/exercises-by-category/exercise/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteExercise(@PathVariable("id") Long id) {
        Long categoryId = exerciseService.deleteExercise(id);

        return "redirect:/exercises/exercises-by-category/" + categoryId;
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

            return "redirect:/exercises/exercises-by-category/exercise/create";
        }

        Long newExerciseId = exerciseService.createExercise(createExerciseDTO);
        return "redirect:/exercises/exercises-by-category/exercise/" + newExerciseId;
    }

    @PostMapping("/{id}/exercise-notes/create")
    public String createExerciseNotes(@PathVariable("id") Long exerciseId,
                                      @Valid CreateExerciseNotesDTO createExerciseNotesDTO,
                                      @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails instanceof KrastevsGymUserDetails krastevsGymUserDetails) {
            Long currentUserId = krastevsGymUserDetails.getCurrId();
            exerciseService.createExerciseNotes(createExerciseNotesDTO, exerciseId, currentUserId);
        }
        return "redirect:/exercises/exercises-by-category/exercise/" + exerciseId;
    }

    @PutMapping("/{id}/exercise-notes/edit")
    public String editExerciseNotes(@PathVariable("id") Long exerciseId,
                                    @Valid CreateExerciseNotesDTO createExerciseNotesDTO,
                                    @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails instanceof KrastevsGymUserDetails krastevsGymUserDetails) {
            Long currentUserId = krastevsGymUserDetails.getCurrId();
            exerciseService.editExerciseNotes(createExerciseNotesDTO, exerciseId, currentUserId);
        }

        return "redirect:/exercises/exercises-by-category/exercise/" + exerciseId;
    }
}
