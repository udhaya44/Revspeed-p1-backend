package com.revature.RevSpeed.services;

import com.revature.RevSpeed.models.BroadbandPlans;
import com.revature.RevSpeed.repositorys.BroadbandPlansRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class BroadbandPlansServiceTest {

    @Mock
    private BroadbandPlansRepository broadbandPlansRepository;

    @InjectMocks
    private BroadbandPlansService broadbandPlansService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBroadbandPlansWithOTTPlatforms_ReturnsList() {
        // Arrange
        List<BroadbandPlans> plansList = new ArrayList<>();
        when(broadbandPlansRepository.findAllWithOTTPlatforms()).thenReturn(plansList);

        // Act
        List<BroadbandPlans> result = broadbandPlansService.getAllBroadbandPlansWithOTTPlatforms();

        // Assert
        assertEquals(plansList, result);
        verify(broadbandPlansRepository, times(1)).findAllWithOTTPlatforms();
    }

    @Test
    void addBroadbandPlanWithOTT_ReturnsSavedBroadbandPlan() {
        // Arrange
        BroadbandPlans broadbandPlan = new BroadbandPlans();
        broadbandPlan.setId(1L);
        broadbandPlan.setPlanName("Test Plan");
        broadbandPlan.setPlanType("Test Type");
        broadbandPlan.setService(new com.revature.RevSpeed.models.Service());
        broadbandPlan.setPrice(10.0);
        broadbandPlan.setSpeed("100 Mbps");
        broadbandPlan.setOtt(new ArrayList<>());

        when(broadbandPlansRepository.save(any(BroadbandPlans.class))).thenReturn(broadbandPlan);

        // Act
        BroadbandPlans result = broadbandPlansService.addBroadbandPlanWithOTT(broadbandPlan);

        // Assert
        assertEquals(broadbandPlan, result);
        verify(broadbandPlansRepository, times(2)).save(any(BroadbandPlans.class));
    }

//    @Test
//    void getDefaultBroadbandPlan_ReturnsOptional() {
//        // Arrange
//        BroadbandPlans defaultPlan = new BroadbandPlans();
//        defaultPlan.setPlanName("no plan name");
//        defaultPlan.setPlanType("No type");
//        defaultPlan.setService(new com.revature.RevSpeed.models.Service());
//        defaultPlan.setPrice(0.00);
//        defaultPlan.setSpeed("00");
//        defaultPlan.setOtt(new ArrayList<>());
//
//        // Act
//        Optional<BroadbandPlans> result = broadbandPlansService.getDefaultBroadbandPlan();
//
//        // Assert
//        assertEquals(Optional.of(defaultPlan), result);
//    }

    @Test
    void getAllBroadbandPlans_ReturnsList() {
        // Arrange
        List<BroadbandPlans> plansList = new ArrayList<>();
        when(broadbandPlansRepository.findAll()).thenReturn(plansList);

        // Act
        List<BroadbandPlans> result = broadbandPlansService.getAllBroadbandPlans();

        // Assert
        assertEquals(plansList, result);
        verify(broadbandPlansRepository, times(1)).findAll();
    }

    @Test
    void deletePlanById_CallsRepositoryDeleteById() {
        // Arrange
        int planId = 1;

        // Act
        broadbandPlansService.deletePlanById(planId);

        // Assert
        verify(broadbandPlansRepository, times(1)).deleteById((long) planId);
    }

}