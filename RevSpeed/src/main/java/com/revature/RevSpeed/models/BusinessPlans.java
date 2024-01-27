package com.revature.RevSpeed.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class BusinessPlans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String planName;
    private String planType;
    private String dataLimit;
    private int validity;
    private double price;
    private String speed;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    public BusinessPlans(String planName, String planType,Service service, String dataLimit , int validity, double price, String speed) {
        this.planName = planName;
        this.planType = planType;
        this.service=service;
        this.dataLimit=dataLimit;
        this.validity=validity;
        this.price = price;
        this.speed = speed;
    }


}
