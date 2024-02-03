package com.revature.RevSpeed.services;

import com.revature.RevSpeed.models.BusinessPlans;
import com.revature.RevSpeed.repositorys.BusinessPlansRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class BusinessPlansServiceTest {

    @Mock
    private BusinessPlansRepository businessPlansRepository;

    @InjectMocks
    private BusinessPlansService businessPlansService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addBusinessPlans_ReturnsSavedBusinessPlan() {
        // Arrange
        BusinessPlans inputBusinessPlan = new BusinessPlans();
        inputBusinessPlan.setPlanName("Test Plan");
        when(businessPlansRepository.save(any(BusinessPlans.class))).thenReturn(inputBusinessPlan);

        // Act
        BusinessPlans result = businessPlansService.addBusinessPlans(inputBusinessPlan);

        // Assert
        assertNotNull(result);
        assertEquals("Test Plan", result.getPlanName());
        verify(businessPlansRepository, times(1)).save(any(BusinessPlans.class));
    }

    @Test
    void getDefaultBusinessPlan_ReturnsDefaultBusinessPlan() {
        // Act
        Optional<BusinessPlans> result = businessPlansService.getDefaultBusinessPlan();

        // Assert
        assertTrue(result.isPresent());
        assertEquals("no name", result.get().getPlanName());
        assertEquals("no", result.get().getPlanType());
        assertEquals("no", result.get().getPrice());
        assertEquals("00", result.get().getSpeed());
    }

    @Test
    void getAllBusinessPlans_ReturnsListOfBusinessPlans() {
        // Arrange
        BusinessPlans plan1 = new BusinessPlans();
        plan1.setId(1L);
        BusinessPlans plan2 = new BusinessPlans();
        plan2.setId(2L);
        when(businessPlansRepository.findAll()).thenReturn(Arrays.asList(plan1, plan2));

        // Act
        List<BusinessPlans> result = businessPlansService.getAllBusinessPlans();

        // Assert
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());
        verify(businessPlansRepository, times(1)).findAll();
    }

    @Test
    void updateBusinessPlan_ReturnsUpdatedBusinessPlan() {
        // Arrange
        BusinessPlans existingBusinessPlan = new BusinessPlans();
        existingBusinessPlan.setId(1L);
        existingBusinessPlan.setPlanName("Existing Plan");

        BusinessPlans updatedBusinessPlan = new BusinessPlans();
        updatedBusinessPlan.setId(1L);
        updatedBusinessPlan.setPlanName("Updated Plan");

        when(businessPlansRepository.findById(1L)).thenReturn(Optional.of(existingBusinessPlan));
        when(businessPlansRepository.save(any(BusinessPlans.class))).thenReturn(updatedBusinessPlan);

        // Act
        BusinessPlans result = businessPlansService.updateBusinessPlan(updatedBusinessPlan);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Plan", result.getPlanName());
        verify(businessPlansRepository, times(1)).findById(1L);
        verify(businessPlansRepository, times(1)).save(any(BusinessPlans.class));
    }

    @Test
    void updateBusinessPlan_ThrowsEntityNotFoundException_WhenBusinessPlanNotFound() {
        // Arrange
        BusinessPlans updatedBusinessPlan = new BusinessPlans();
        updatedBusinessPlan.setId(1L);
        updatedBusinessPlan.setPlanName("Updated Plan");

        when(businessPlansRepository.findById(1L)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> businessPlansService.updateBusinessPlan(updatedBusinessPlan));
        verify(businessPlansRepository, times(1)).findById(1L);
        verify(businessPlansRepository, times(0)).save(any(BusinessPlans.class));
    }


}