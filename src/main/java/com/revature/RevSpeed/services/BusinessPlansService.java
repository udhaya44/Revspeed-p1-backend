package com.revature.RevSpeed.services;

import com.revature.RevSpeed.models.BusinessPlans;
import com.revature.RevSpeed.models.PlanType;
import com.revature.RevSpeed.repositorys.BusinessPlansRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessPlansService {
    @Autowired
    private BusinessPlansRepository businessPlansRepository;

    public BusinessPlans addBusinessPlans(BusinessPlans businessPlans){
        return businessPlansRepository.save(businessPlans);
    }

    public Optional<BusinessPlans> getDefaultBusinessPlan() {
        BusinessPlans businessPlans=new BusinessPlans();
        businessPlans.setPlanType("no");
        businessPlans.setPlanName("no name");
        businessPlans.setPrice("no");
        businessPlans.setSpeed("00");
        return Optional.of(businessPlans);
    }

    public List<BusinessPlans> getAllBusinessPlans(){
        return businessPlansRepository.findAll();
    }

    // udays code

    public List<BusinessPlans> getAllplans() {
        return businessPlansRepository.findAll();
    }

    public void deletePlanById(int planId) {
        businessPlansRepository.deleteById((long) planId);
    }

    public BusinessPlans updateBusinessPlan(BusinessPlans updatedBusinessPlan) {

        BusinessPlans existingBusinessPlan = businessPlansRepository.findById(updatedBusinessPlan.getId())
                .orElseThrow(() -> new EntityNotFoundException("Business Plan not found with id: " + updatedBusinessPlan.getId()));

        // Update the fields you want to change
        existingBusinessPlan.setPlanName(updatedBusinessPlan.getPlanName());
        existingBusinessPlan.setPlanType(updatedBusinessPlan.getPlanType());
        existingBusinessPlan.setPrice(updatedBusinessPlan.getPrice());
        existingBusinessPlan.setSpeed(updatedBusinessPlan.getSpeed());
        existingBusinessPlan.setValidity(updatedBusinessPlan.getValidity());
        existingBusinessPlan.setDataLimit(updatedBusinessPlan.getDataLimit());

        return businessPlansRepository.save(existingBusinessPlan);
    }


}
