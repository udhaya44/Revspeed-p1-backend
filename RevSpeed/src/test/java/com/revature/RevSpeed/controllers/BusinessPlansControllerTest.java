package com.revature.RevSpeed.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.RevSpeed.models.BusinessPlans;
import com.revature.RevSpeed.services.BusinessPlansService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
class BusinessPlansControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BusinessPlansService businessPlansService;

    @InjectMocks
    private BusinessPlansController businessPlansController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addBusinessPlans_ReturnsAddedBusinessPlan() throws Exception {
        // Arrange
        BusinessPlans inputBusinessPlan = new BusinessPlans();
        inputBusinessPlan.setPlanName("Test Plan");
        when(businessPlansService.addBusinessPlans(any(BusinessPlans.class))).thenReturn(inputBusinessPlan);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/businessplans/addBusinessplan")
                        .content(asJsonString(inputBusinessPlan))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.planName").value("Test Plan"));
    }

    @Test
    void getAllBusinessPlan_ReturnsListOfBusinessPlans() throws Exception {
        // Arrange
        BusinessPlans plan1 = new BusinessPlans();
        plan1.setId(1L);
        BusinessPlans plan2 = new BusinessPlans();
        plan2.setId(2L);
        when(businessPlansService.getAllBusinessPlans()).thenReturn(Arrays.asList(plan1, plan2));

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/businessplans/getAllBusinessPlan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    void updateBusinessPlan_ReturnsUpdatedBusinessPlan() throws Exception {
        // Arrange
        BusinessPlans existingBusinessPlan = new BusinessPlans();
        existingBusinessPlan.setId(1L);
        existingBusinessPlan.setPlanName("Existing Plan");

        BusinessPlans updatedBusinessPlan = new BusinessPlans();
        updatedBusinessPlan.setId(1L);
        updatedBusinessPlan.setPlanName("Updated Plan");

        when(businessPlansService.updateBusinessPlan(any(BusinessPlans.class))).thenReturn(updatedBusinessPlan);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/businessplans/updatePlan/1")
                        .content(asJsonString(updatedBusinessPlan))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.planName").value("Updated Plan"));
    }

    @Test
    void deletePlan_ReturnsSuccessMessage() throws Exception {
        // Arrange
        int planId = 1;
        doNothing().when(businessPlansService).deletePlanById(planId);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/businessplans/deletePlan/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Plan deleted successfully"));
    }

    // Add more test cases as needed

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}