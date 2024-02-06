package com.revature.RevSpeed.dto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


public class UserActivePlanDetails {
    private String firstName;
    private String lastName;
    private String userId;
    private LocalDate subscriptionStartDate;
    private LocalDate subscriptionEndDate;
    private Boolean broadbandActive;

    // Constructor matching the query result order and types
    public UserActivePlanDetails(String firstName, String lastName, String userId, LocalDate subscriptionStartDate, LocalDate subscriptionEndDate, Boolean broadbandActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
        this.subscriptionStartDate = subscriptionStartDate;
        this.subscriptionEndDate = subscriptionEndDate;
        this.broadbandActive = broadbandActive;
    }


    public UserActivePlanDetails() {
    }
}


