package com.revature.RevSpeed.services;

import com.revature.RevSpeed.models.BroadbandPlans;
import com.revature.RevSpeed.models.OTT;
import com.revature.RevSpeed.repositorys.BroadbandPlansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BroadbandPlansService {
    @Autowired
    private BroadbandPlansRepository broadbandPlansRepository;

    public List<BroadbandPlans> getAllBroadbandPlansWithOTTPlatforms() {
        return broadbandPlansRepository.findAllWithOTTPlatforms();
    }


    public BroadbandPlans addBroadbandPlanWithOTT(BroadbandPlans broadbandPlan) {
        // Save the BroadbandPlans instance to generate the ID
        BroadbandPlans savedBroadbandPlan = broadbandPlansRepository.save(broadbandPlan);

        // Create OTT instances and associate them with the BroadbandPlans
        List<OTT> ottList = broadbandPlan.getOtt();
        if (ottList != null) {
            for (OTT ott : ottList) {
                ott.setBroadbandPlan(savedBroadbandPlan);
            }
        }

        // Save the BroadbandPlans instance again to update the relationship
        return broadbandPlansRepository.save(savedBroadbandPlan);
    }


    public Optional<BroadbandPlans> getDefaultBroadbandPlan() {
        BroadbandPlans broadbandPlans=new BroadbandPlans();
        broadbandPlans.setPlanName("no plan name");
        broadbandPlans.setPlanType("No type");
        broadbandPlans.setService(new com.revature.RevSpeed.models.Service());
        broadbandPlans.setPrice(0.00);
        broadbandPlans.setSpeed("00");
        broadbandPlans.setOtt(new ArrayList<>());

        return Optional.of(broadbandPlans);
    }

    public List<BroadbandPlans> getAllBroadbandPlans() {
        return broadbandPlansRepository.findAll();
    }


    // udays code

    public void deletePlanById(int planId) {
        System.out.println(planId+"dfghj");
        broadbandPlansRepository.deleteById((long) planId);
    }
}
