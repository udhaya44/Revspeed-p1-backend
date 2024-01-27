package com.revature.RevSpeed.services;

import com.revature.RevSpeed.models.BusinessPlans;
import com.revature.RevSpeed.models.PlanType;
import com.revature.RevSpeed.repositorys.BusinessPlansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        businessPlans.setPrice(0.00);
        businessPlans.setSpeed("00");
        return Optional.of(businessPlans);
    }
}
