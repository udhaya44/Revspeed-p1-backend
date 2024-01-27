package com.revature.RevSpeed.controllers;

import com.revature.RevSpeed.models.BroadbandPlans;
import com.revature.RevSpeed.services.BroadbandPlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/broadbandplans")
public class BroadbandplansController {

    @Autowired
    private BroadbandPlansService broadbandPlansService;
    @GetMapping("/GetAllBroadBandPlanswithott")
    public List<BroadbandPlans> getAllBroadBnadPlans(){
        return  broadbandPlansService.getAllBroadbandPlansWithOTTPlatforms();

    }


    @PostMapping("/add")
    public BroadbandPlans addBroadbandPlanWithOTT(@RequestBody BroadbandPlans broadbandPlan) {
        return broadbandPlansService.addBroadbandPlanWithOTT(broadbandPlan);
    }


}
