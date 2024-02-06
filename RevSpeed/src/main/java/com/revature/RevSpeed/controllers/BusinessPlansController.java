package com.revature.RevSpeed.controllers;

import com.revature.RevSpeed.models.BusinessPlans;
import com.revature.RevSpeed.services.BusinessPlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/businessplans")
@CrossOrigin(origins = "*")
public class BusinessPlansController {

    @Autowired
    private BusinessPlansService businessPlansService;

    @PostMapping("/addBusinessplan")
    public BusinessPlans addBusinessPlans(@RequestBody BusinessPlans businessPlans){
        return businessPlansService.addBusinessPlans(businessPlans);
    }

    @GetMapping("/getAllBusinessPlan")
    public List<BusinessPlans> getAllBusinessPlan(){
        return businessPlansService.getAllBusinessPlans();
    }

    // udayas

    @GetMapping("/getbusinessplans")
    public List<BusinessPlans> getAllBroadBnadPlans(){
        return  businessPlansService.getAllplans();
    }

    @PutMapping("/updatePlan/{planId}")
    public ResponseEntity<BusinessPlans> updateBusinessPlan(@PathVariable Long planId,
                                                            @RequestBody BusinessPlans updatedBusinessPlan) {
        // Ensure that the provided planId matches the id of the business plan
        if (planId == null || !planId.equals(updatedBusinessPlan.getId())) {
            return ResponseEntity.badRequest().build();
        }
        BusinessPlans updatedPlan = businessPlansService.updateBusinessPlan(updatedBusinessPlan);

        return ResponseEntity.ok(updatedPlan);
    }

    @DeleteMapping("/deletePlan/{planId}")
    public ResponseEntity<String> deletePlan(@PathVariable int planId) {
        businessPlansService.deletePlanById(planId);
        return ResponseEntity.ok("Plan deleted successfully");
    }


}
