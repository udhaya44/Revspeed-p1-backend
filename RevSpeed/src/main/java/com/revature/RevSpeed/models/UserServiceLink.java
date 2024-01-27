package com.revature.RevSpeed.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class UserServiceLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "broadband_plan_id")
    private BroadbandPlans broadbandPlans;

    @ManyToOne
    @JoinColumn(name = "business_plan_id")
    private BusinessPlans businessPlans;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    private LocalDate subscriptionStartDate;

    private LocalDate subscriptionEndDate;


    @Column(nullable = false)
    private Boolean broadbandActive=false;

    @Column(nullable = false)
    private Boolean BusinessIsActive=false;
}
