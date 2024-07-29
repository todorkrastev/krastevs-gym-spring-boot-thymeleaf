package com.todorkrastev.krastevsgym.web;

import com.todorkrastev.krastevsgym.exception.UnauthorizedException;
import com.todorkrastev.krastevsgym.model.dto.CreateExerciseDTO;
import com.todorkrastev.krastevsgym.model.dto.CreateExerciseNotesDTO;
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
    public String exerciseDetails(@PathVariable("id") Long id,
                                  Model model,
                                  @AuthenticationPrincipal UserDetails userDetails) {
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

    @GetMapping("/{id}/edit")
    public String editExercise(@PathVariable("id") Long id,
                               Model model,
                               @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails instanceof KrastevsGymUserDetails krastevsGymUserDetails) {
            Long currentUserId = krastevsGymUserDetails.getCurrId();

            if (!exerciseService.isTheCreatorOfTheExercise(id, currentUserId)) {
                throw new UnauthorizedException("You are not the creator of this exercise!");
            }

            ExerciseDetailsDTO exerciseDetailsDTO = exerciseService.getExerciseDetails(id, currentUserId);
            model.addAttribute("exerciseDetailsDTO", exerciseDetailsDTO);
        }

        return "exercise-edit";
    }

    @PutMapping("/{id}/edit")
    public String editExercise(@PathVariable("id") Long id,
                               @Valid ExerciseDetailsDTO exerciseDetailsDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               Model model,
                               @AuthenticationPrincipal UserDetails userDetails) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("exerciseDetailsDTO", exerciseDetailsDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.exerciseDetailsDTO", bindingResult);

            return "redirect:/exercises/exercises-by-category/exercise/edit/error";
        }

        if (userDetails instanceof KrastevsGymUserDetails krastevsGymUserDetails) {
            Long currentUserId = krastevsGymUserDetails.getCurrId();

            ExerciseDetailsDTO exerciseDetails = exerciseService.editExercise(id, exerciseDetailsDTO, currentUserId);
            model.addAttribute("exerciseDetails", exerciseDetails);
        }

        return "redirect:/exercises/exercises-by-category/exercise/" + id;
    }

    @GetMapping("/edit/error")
    public String editExerciseError(Model model) {
        if (!model.containsAttribute("exerciseDetailsDTO")) {
            model.addAttribute("exerciseDetailsDTO", new ExerciseDetailsDTO());
        }

        return "exercise-edit";
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
