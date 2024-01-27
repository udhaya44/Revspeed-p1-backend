package com.revature.RevSpeed.controllers;

import com.revature.RevSpeed.models.BusinessPlans;
import com.revature.RevSpeed.services.BusinessPlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/businessplans")
public class BusinessPlansController {

    @Autowired
    private BusinessPlansService businessPlansService;

    @PostMapping("/addBusinessplan")
    public BusinessPlans addBusinessPlans(@RequestBody BusinessPlans businessPlans){
        return businessPlansService.addBusinessPlans(businessPlans);
    }
}
