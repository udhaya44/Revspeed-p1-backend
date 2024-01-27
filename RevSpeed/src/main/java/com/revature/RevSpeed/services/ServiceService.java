package com.revature.RevSpeed.services;

import com.revature.RevSpeed.repositorys.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public com.revature.RevSpeed.models.Service addServices(com.revature.RevSpeed.models.Service myservice){
        return serviceRepository.save(myservice);
    }
}
