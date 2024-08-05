package com.todorkrastev.krastevsgym.web;

import com.todorkrastev.krastevsgym.model.entity.EmployeeEntity;
import com.todorkrastev.krastevsgym.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TeamControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    private Long employeeId;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();

        EmployeeEntity employee = new EmployeeEntity()
                .setFirstName("Florian")
                .setLastName("Dewitz")
                .setPosition("Personal Trainer")
                .setPhoneNumber("1234567890")
                .setEmail("florian.dewitz@example.com")
                .setImageUrl("https://example.com/image.jpg")
                .setQualifications("Certified Personal Trainer")
                .setPerformanceModule("Performance Module")
                .setPersonalInfo("Personal Info");

        employeeId = employeeRepository.save(employee).getId();
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "user", password = "topsecret")
    void testAllEmployees() throws Exception {
        mockMvc.perform(get("/team/all-employees"))
                .andExpect(status().isOk())
                .andExpect(view().name("team"))
                .andExpect(model().attributeExists("employees"))
                .andExpect(model().size(1));
    }

    @Test
    @WithMockUser(username = "admin", password = "topsecret", roles = {"ADMIN"})
    void testEmployeeDetails() throws Exception {
        mockMvc.perform(get("/team/employee/" + employeeId))
                .andExpect(status().isOk())
                .andExpect(view().name("team-employee"))
                .andExpect(model().attributeExists("employee"));
    }
}