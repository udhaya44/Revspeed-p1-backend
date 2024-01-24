package com.revature.RevSpeed.controllers;

import com.revature.RevSpeed.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

//    @GetMapping("user")
//    public List<User> getUser(){
//
//        return this.userService.getUsers();
//    }

    @GetMapping("/create-admin")
    public String getLoggedInUser(Principal principal){
        return "hi aakash this is admin" ;  // return the name of current login in user
    }
}
