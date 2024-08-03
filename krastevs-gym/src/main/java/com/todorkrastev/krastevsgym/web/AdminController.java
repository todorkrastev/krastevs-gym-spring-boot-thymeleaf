package com.todorkrastev.krastevsgym.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.todorkrastev.krastevsgym.exception.ResourceNotFoundException;
import com.todorkrastev.krastevsgym.exception.UnauthorizedException;
import com.todorkrastev.krastevsgym.model.dto.ActivityDTO;
import com.todorkrastev.krastevsgym.model.dto.CreateActivityDTO;
import com.todorkrastev.krastevsgym.model.user.KrastevsGymUserDetails;
import com.todorkrastev.krastevsgym.service.ActivityService;
import com.todorkrastev.krastevsgym.service.UserService;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ActivityService activityService;
    private final UserService userService;
    private final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AdminController.class);

    public AdminController(ActivityService activityService, UserService userService) {
        this.activityService = activityService;
        this.userService = userService;
    }

    @ModelAttribute("createActivityDTO")
    public CreateActivityDTO createActivityDTO() {
        return new CreateActivityDTO();
    }


    @GetMapping("/activities/all")
    public String allActivities(Model model,
                                @AuthenticationPrincipal UserDetails userDetails) {
        isAdminLogged(userDetails);

        List<ActivityDTO> activities = activityService.findAll();
        model.addAttribute("activities", activities);

        return "admin-activities";
    }

    @GetMapping("/activities/create")
    public String createActivity(@AuthenticationPrincipal UserDetails userDetails) {
        isAdminLogged(userDetails);

        return "admin-activity-create";
    }

    @PostMapping("/activities/create")
    public String createActivity(CreateActivityDTO createActivityDTO,
                                 @AuthenticationPrincipal UserDetails userDetails,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) throws JsonProcessingException {
        isAdminLogged(userDetails);

        try {
            activityService.createActivity(createActivityDTO);
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().value() == HttpStatus.BAD_REQUEST.value()) {
                Map<String, String> errors = new ObjectMapper().readValue(ex.getResponseBodyAsString(), new TypeReference<>() {
                });
                errors.forEach((field, errorMessage) -> bindingResult.rejectValue(field, "error." + field, errorMessage));
                redirectAttributes.addFlashAttribute("createActivityDTO", createActivityDTO);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createActivityDTO", bindingResult);
                return "redirect:/admin/activities/create";
            } else {
                LOGGER.error("Error occurred while creating activity: ", ex);
                throw new RuntimeException("Error occurred while creating activity", ex);
            }
        }

        return "redirect:/admin/activities/all";
    }

    @GetMapping("/activities/edit/{id}")
    public String editActivity(@PathVariable("id") Long id,
                               Model model,
                               @AuthenticationPrincipal UserDetails userDetails) {
        isAdminLogged(userDetails);

        try {
            ActivityDTO activityDTO = activityService.getActivityById(id);
            model.addAttribute("activityDTO", activityDTO);
            return "admin-activity-edit";
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
                throw new ResourceNotFoundException("Activity", "id", id);
            } else {
                LOGGER.error("Error occurred while fetching activity: ", ex);
                throw new RuntimeException("Error occurred while fetching activity", ex);
            }
        }
    }

    @PutMapping("/activities/edit/{id}")
    public String updateActivity(@PathVariable("id") Long id,
                                 ActivityDTO activityDTO,
                                 @AuthenticationPrincipal UserDetails userDetails,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) throws JsonProcessingException {
        isAdminLogged(userDetails);

        try {
            activityService.updateActivity(id, activityDTO);
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().value() == HttpStatus.BAD_REQUEST.value()) {
                Map<String, String> errors = new ObjectMapper().readValue(ex.getResponseBodyAsString(), new TypeReference<>() {
                });
                errors.forEach((field, errorMessage) -> bindingResult.rejectValue(field, "error." + field, errorMessage));
                redirectAttributes.addFlashAttribute("activityDTO", activityDTO);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.activityDTO", bindingResult);
                return "redirect:/admin/activities/edit/error";
            } else {
                LOGGER.error("Error occurred while updating activity: ", ex);
                throw new RuntimeException("Error occurred while updating activity", ex);
            }
        }

        return "redirect:/admin/activities/" + id;
    }

    @GetMapping("/activities/edit/error")
    public String editActivityError(Model model) {
        if (!model.containsAttribute("activityDTO")) {
            model.addAttribute("activityDTO", new ActivityDTO());
        }

        return "admin-activity-edit";
    }



    @GetMapping("/activities/{id}")
    public String getActivityById(@PathVariable("id") Long id,
                                  Model model,
                                  @AuthenticationPrincipal UserDetails userDetails) {
        isAdminLogged(userDetails);
        try {
            ActivityDTO activityDTO = activityService.getActivityById(id);
            model.addAttribute("activityDTO", activityDTO);
            return "admin-activity-details";
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
                throw new ResourceNotFoundException("Activity", "id", id);
            } else {
                LOGGER.error("Error occurred while fetching activity: ", ex);
                throw new RuntimeException("Error occurred while fetching activity", ex);
            }
        }
    }

    @DeleteMapping("/activities/{id}")
    public String deleteActivity(@PathVariable("id") Long id,
                                 @AuthenticationPrincipal UserDetails userDetails) {
        isAdminLogged(userDetails);

        try {
            activityService.deleteActivity(id);
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
                throw new ResourceNotFoundException("Activity", "id", id);
            } else {
                LOGGER.error("Error occurred while deleting activity: ", ex);
                throw new RuntimeException("Error occurred while deleting activity", ex);
            }
        }

        return "redirect:/admin/activities/all";
    }


    private void isAdminLogged(UserDetails userDetails) {
        if (userDetails instanceof KrastevsGymUserDetails krastevsGymUserDetails) {
            Long currentUserId = krastevsGymUserDetails.getCurrId();
            Long adminId = userService.findAdminId();
            if (!Objects.equals(currentUserId, adminId)) {
                throw new UnauthorizedException("You are not authorized to access this page!");
            }
        }
    }
}
