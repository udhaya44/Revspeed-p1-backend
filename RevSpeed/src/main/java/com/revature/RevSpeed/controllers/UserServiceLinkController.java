package com.revature.RevSpeed.controllers;

import com.revature.RevSpeed.models.UserServiceLink;
import com.revature.RevSpeed.services.UserServiceLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("userservicelink")
public class UserServiceLinkController {
    private final UserServiceLinkService userServiceLinkService;

    @Autowired
    public UserServiceLinkController(UserServiceLinkService userServiceLinkService) {
        this.userServiceLinkService = userServiceLinkService;
    }

    @PostMapping("/linkuserservice")
    public ResponseEntity<UserServiceLink> createUserServiceLink(@RequestBody UserServiceLink userServiceLink) {
        UserServiceLink savedUserServiceLink = userServiceLinkService.saveUserServiceLink(userServiceLink);
        return new ResponseEntity<>(savedUserServiceLink, HttpStatus.CREATED);
    }

    @GetMapping("/getUserServicesDetails/{userId}")
    public List<UserServiceLink> getUserServiceDetails(@PathVariable String userId) {
        return userServiceLinkService.findUserServiceDetailsByUserId(userId);
    }
}
