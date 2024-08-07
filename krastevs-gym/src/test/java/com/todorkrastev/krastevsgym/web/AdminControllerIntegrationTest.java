package com.todorkrastev.krastevsgym.web;

import com.todorkrastev.krastevsgym.exception.ResourceNotFoundException;
import com.todorkrastev.krastevsgym.model.dto.ActivityDTO;
import com.todorkrastev.krastevsgym.model.dto.CreateActivityDTO;
import com.todorkrastev.krastevsgym.service.ActivityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActivityService activityService;

    @Test
    @WithMockUser(username = "admin@admin.com", password = "admin", roles = {"ADMIN"})
    void testAllActivities() throws Exception {
        List<ActivityDTO> activities =
                List.of(
                        new ActivityDTO()
                                .setId(1L)
                                .setTitle("Activity 1")
                                .setImageURL("Image URL 1")
                                .setDescription("Description 1"),
                        new ActivityDTO()
                                .setId(2L)
                                .setTitle("Activity 2")
                                .setImageURL("Image URL 2")
                                .setDescription("Description 2"));

        when(activityService.findAll()).thenReturn(activities);

        mockMvc.perform(get("/admin/activities/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin-activities"))
                .andExpect(model().attributeExists("activities"))
                .andExpect(model().attribute("activities", activities));
    }

    @Test
    @WithMockUser(username = "admin@admin.com", password = "admin", roles = {"ADMIN"})
    void testCreateActivity_GET() throws Exception {
        mockMvc.perform(get("/admin/activities/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin-activity-create"));
    }

    @Test
    @WithMockUser(username = "admin@admin.com", password = "admin", roles = {"ADMIN"})
    void testCreateActivity() throws Exception {
        CreateActivityDTO createActivityDTO =
                new CreateActivityDTO()
                        .setTitle("New Activity 1")
                        .setImageURL("New Image URL 1")
                        .setDescription("New Description 1");

        mockMvc.perform(post("/admin/activities/create")
                        .with(csrf())
                        .flashAttr("createActivityDTO", createActivityDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/activities/all"));
    }

    @Test
    @WithMockUser(username = "admin@admin.com", password = "admin", roles = {"ADMIN"})
    void testCreateActivity_BadRequest() throws Exception {
        CreateActivityDTO createActivityDTO =
                new CreateActivityDTO()
                        .setTitle("New Activity 1")
                        .setImageURL("New Image URL 1")
                        .setDescription("New Description 1");

        RestClientResponseException exception = new RestClientResponseException(
                "Validation failed", HttpStatus.BAD_REQUEST.value(), "Bad Request", null, "{\"title\":\"Title is required\"}".getBytes(), null);
        doThrow(exception).when(activityService).createActivity(createActivityDTO);

        mockMvc.perform(post("/admin/activities/create")
                        .with(csrf())
                        .flashAttr("createActivityDTO", createActivityDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/activities/create"))
                .andExpect(flash().attributeExists("createActivityDTO"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.createActivityDTO"));
    }

    @Test
    @WithMockUser(username = "admin@admin.com", password = "admin", roles = {"ADMIN"})
    void testEditActivity() throws Exception {
        ActivityDTO activityDTO =
                new ActivityDTO()
                        .setTitle("Edited Activity 1")
                        .setImageURL("Edited Image URL 1")
                        .setDescription("Edited Description 1");

        when(activityService.getActivityById(1L)).thenReturn(activityDTO);

        mockMvc.perform(get("/admin/activities/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin-activity-edit"))
                .andExpect(model().attributeExists("activityDTO"))
                .andExpect(model().attribute("activityDTO", activityDTO));
    }

    @Test
    @WithMockUser(username = "admin@admin.com", password = "admin", roles = {"ADMIN"})
    void testEditActivity_NotFound() throws Exception {
        Long activityId = 1L;

        RestClientResponseException exception = new RestClientResponseException(
                "Not Found", HttpStatus.NOT_FOUND.value(), "Not Found", null, null, null);
        doThrow(exception).when(activityService).getActivityById(activityId);

        mockMvc.perform(get("/admin/activities/edit/{id}", activityId))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(ResourceNotFoundException.class, result.getResolvedException()))
                .andExpect(result -> assertEquals("Activity not found with id : '1'", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    @WithMockUser(username = "admin@admin.com", password = "admin", roles = {"ADMIN"})
    void testEditActivityError() throws Exception {
        mockMvc.perform(get("/admin/activities/edit/error"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin-activity-edit"))
                .andExpect(model().attributeExists("activityDTO"));
    }

    @Test
    @WithMockUser(username = "admin@admin.com", password = "admin", roles = {"ADMIN"})
    void testGetActivityById_Success() throws Exception {
        Long activityId = 1L;
        ActivityDTO activityDTO = new ActivityDTO()
                .setId(activityId)
                .setTitle("Activity 1")
                .setImageURL("Image URL 1")
                .setDescription("Description 1");

        when(activityService.getActivityById(activityId)).thenReturn(activityDTO);

        mockMvc.perform(get("/admin/activities/{id}", activityId))
                .andExpect(status().isOk())
                .andExpect(view().name("admin-activity-details"))
                .andExpect(model().attributeExists("activityDTO"))
                .andExpect(model().attribute("activityDTO", activityDTO));
    }

    @Test
    @WithMockUser(username = "admin@admin.com", password = "admin", roles = {"ADMIN"})
    void testGetActivityById_NotFound() throws Exception {
        Long activityId = 1L;

        RestClientResponseException exception = new RestClientResponseException(
                "Not Found", HttpStatus.NOT_FOUND.value(), "Not Found", null, null, null);
        doThrow(exception).when(activityService).getActivityById(activityId);

        mockMvc.perform(get("/admin/activities/{id}", activityId))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(ResourceNotFoundException.class, result.getResolvedException()))
                .andExpect(result -> assertEquals("Activity not found with id : '1'", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    @WithMockUser(username = "admin@admin.com", password = "admin", roles = {"ADMIN"})
    void testUpdateActivity() throws Exception {
        ActivityDTO activityDTO = new ActivityDTO()
                .setTitle("Updated Activity")
                .setImageURL("Updated Image URL")
                .setDescription("Updated Description");


        mockMvc.perform(put("/admin/activities/edit/1")
                        .with(csrf())
                        .flashAttr("activityDTO", activityDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/activities/1"));
    }

    @Test
    @WithMockUser(username = "admin@admin.com", password = "admin", roles = {"ADMIN"})
    void testUpdateActivity_BadRequest() throws Exception {
        Long activityId = 1L;
        ActivityDTO activityDTO = new ActivityDTO()
                .setTitle("Updated Activity")
                .setImageURL("Updated Image URL")
                .setDescription("Updated Description");

        RestClientResponseException exception = new RestClientResponseException(
                "Validation failed", HttpStatus.BAD_REQUEST.value(), "Bad Request", null, "{\"title\":\"Title is required\"}".getBytes(), null);
        doThrow(exception).when(activityService).updateActivity(activityId, activityDTO);

        mockMvc.perform(put("/admin/activities/edit/{id}", activityId)
                        .with(csrf())
                        .flashAttr("activityDTO", activityDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/activities/edit/error"))
                .andExpect(flash().attributeExists("activityDTO"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.activityDTO"));
    }

    @Test
    @WithMockUser(username = "admin@admin.com", password = "admin", roles = {"ADMIN"})
    void testDeleteActivity() throws Exception {
        mockMvc.perform(delete("/admin/activities/1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/activities/all"));
    }


    @Test
    @WithMockUser(username = "admin@admin.com", password = "admin", roles = {"ADMIN"})
    void testDeleteActivity_NotFound() throws Exception {
        Long activityId = 1L;

        RestClientResponseException exception = new RestClientResponseException(
                "Not Found", HttpStatus.NOT_FOUND.value(), "Not Found", null, null, null);
        doThrow(exception).when(activityService).deleteActivity(activityId);

        mockMvc.perform(delete("/admin/activities/{id}", activityId)
                        .with(csrf()))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(ResourceNotFoundException.class, result.getResolvedException()))
                .andExpect(result -> assertEquals("Activity not found with id : '1'", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}