package com.revature.RevSpeed.controllers;

import com.revature.RevSpeed.models.Service;
import com.revature.RevSpeed.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @PostMapping("/addServices")
    public Service addServices(@RequestBody Service service){
        return serviceService.addServices(service);
    }

}
