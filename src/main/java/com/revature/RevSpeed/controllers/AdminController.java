package com.revature.RevSpeed.controllers;

import com.revature.RevSpeed.dto.SignUpRequest;
import com.revature.RevSpeed.models.User;
import com.revature.RevSpeed.models.UserServiceLink;
import com.revature.RevSpeed.services.AdminService;
import com.revature.RevSpeed.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

// hi
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
//@CrossOrigin(origins = {"http://localhost:4200"})
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @GetMapping("/getUsers")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return adminService.getUserById(userId);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return adminService.createUser(user);
    }

//    @PutMapping("/users/{userId}")
//    public User updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
//        return adminService.updateUser(userId, updatedUser);
//    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        adminService.deleteUser(userId);
    }

    @GetMapping("/create-admin")
    public String getLoggedInUser(Principal principal){
        return "hi aakash this is admin" ;  // return the name of current login in user
    }

    @GetMapping("/getactivesub")
    public List<?> getActiveSub() {
        return adminService.getActiveSub();
    }

    @PostMapping("/createBusinessUser")
    public User createUser(@RequestBody SignUpRequest signUpRequest){
        return userService.createBusinessUser(signUpRequest);
    }

    @PostMapping("/updateBusinessUser/{userId}")
    public void updateBusinessUser(@PathVariable String userId ,@RequestBody User user){
        userService.updateUsersDetails(userId,user);
    }

    @GetMapping("/getUserServiceLikeTable")
    public List<UserServiceLink> getUserServiceLikeTable(){
        return adminService.getUserServiceLinkTableData();
}
}
