package com.revature.RevSpeed.controllers;

import com.revature.RevSpeed.dto.UserActivePlanDetails;
import com.revature.RevSpeed.models.UserServiceLink;
import com.revature.RevSpeed.services.UserServiceLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("userservicelink")
@CrossOrigin(origins = "*")
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

//    @GetMapping("/details/{userId}/{broadbandActive}")
//    public List<UserServiceLink> getUserServiceDetails(
//            @PathVariable String userId,
//            @PathVariable Boolean broadbandActive) {
//        return userServiceLinkService.findByUserIdAndBroadbandActive(userId, broadbandActive);
//    }


}
