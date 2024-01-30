package com.revature.RevSpeed.services;

import com.revature.RevSpeed.models.User;
import com.revature.RevSpeed.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(String.valueOf(userId)).orElse(null);
    }

    public User createUser(User user) {
        // You may want to perform additional validation before saving
        return userRepository.save(user);
    }

//    public User updateUser(Long userId, User updatedUser) {
//        Optional<User> existingUserOptional = userRepository.findById(String.valueOf(userId));
//
//        if (existingUserOptional.isPresent()) {
//            User existingUser = existingUserOptional.get();
//            // Update the existing user with the new information
//            // Make sure to handle updating only the fields you want to allow
//            existingUser.setUsername(updatedUser.getUsername());
//            existingUser.setPassword(updatedUser.getPassword());
//            // Update other fields as needed
//
//            return userRepository.save(existingUser);
//        }
//
//        return null; // User not found
//    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(String.valueOf(userId));
    }

    public List<Map<String, Object>> getActiveSub(){
        return userRepository.getActiveSub();
    }
}