package com.revature.RevSpeed.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.RevSpeed.models.BroadbandPlans;
import com.revature.RevSpeed.services.BroadbandPlansService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BroadbandplansControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BroadbandPlansService broadbandPlansService;

    @Test
    void getAllBroadBnadPlans_ReturnsListOfBroadbandPlans() throws Exception {
        // Arrange
        List<BroadbandPlans> plansList = new ArrayList<>();
        when(broadbandPlansService.getAllBroadbandPlansWithOTTPlatforms()).thenReturn(plansList);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/broadbandplans/GetAllBroadBandPlanswithott"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

        // Verify the service method is called
        verify(broadbandPlansService, times(1)).getAllBroadbandPlansWithOTTPlatforms();
    }

    @Test
    void addBroadbandPlanWithOTT_ReturnsAddedBroadbandPlan() throws Exception {
        // Arrange
        BroadbandPlans broadbandPlan = new BroadbandPlans();
        broadbandPlan.setId(1L);
        when(broadbandPlansService.addBroadbandPlanWithOTT(any(BroadbandPlans.class))).thenReturn(broadbandPlan);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/broadbandplans/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(broadbandPlan)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));

        // Verify the service method is called
        verify(broadbandPlansService, times(1)).addBroadbandPlanWithOTT(any(BroadbandPlans.class));
    }

    @Test
    void getAllBroadbandPlans_ReturnsListOfAllBroadbandPlans() throws Exception {
        // Arrange
        List<BroadbandPlans> plansList = new ArrayList<>();
        when(broadbandPlansService.getAllBroadbandPlans()).thenReturn(plansList);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/broadbandplans/getAllplans"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

        // Verify the service method is called
        verify(broadbandPlansService, times(1)).getAllBroadbandPlans();
    }

//    @Test
//    void deletePlan_ReturnsSuccessMessage() throws Exception {
//        // Arrange
//        int planId = 1;
//
//        // Act & Assert
//        mockMvc.perform(MockMvcRequestBuilders.delete("/broadbandplans/deletePlan/{planId}", planId))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.message").value("Plan deleted successfully"));
//
//        // Verify the service method is called
//        verify(broadbandPlansService, times(1)).deletePlanById(planId);
//    }


}