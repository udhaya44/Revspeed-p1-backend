package com.revature.RevSpeed.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class OTT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String ottName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "broadband_plan_id")
    private BroadbandPlans broadbandPlan;

    public OTT(String ottName, BroadbandPlans broadbandPlan) {
        this.ottName = ottName;
        this.broadbandPlan = broadbandPlan;
    }
}
