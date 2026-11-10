package com.example.onboarding_tracker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest; // <-- Notice it ends in SpringBootTest, NOT SpringBootApplicationTest
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OnboardingTrackerApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCaptureEmployeeSuccessfully() throws Exception {
        String validEmployeeJson = """
                {
                    "name": "Jane Doe",
                    "role": "Software Engineer",
                    "onboardingDate": "2026-11-10"
                }
                """;

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validEmployeeJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.role").value("Software Engineer"))
                .andExpect(jsonPath("$.onboardingDate").value("2026-11-10"));
    }

    @Test
    void shouldFailValidationWhenFieldsAreBlank() throws Exception {
        String invalidEmployeeJson = """
                {
                    "name": "",
                    "role": "   ",
                    "onboardingDate": "2026-11-10"
                }
                """;

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidEmployeeJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldFetchAllEmployeesSuccessfully() throws Exception {
        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

}
