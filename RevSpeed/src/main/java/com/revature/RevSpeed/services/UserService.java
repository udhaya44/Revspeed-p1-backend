package com.revature.RevSpeed.services;

import com.revature.RevSpeed.dto.SignUpRequest;
import com.revature.RevSpeed.models.Role;
import com.revature.RevSpeed.models.User;
import com.revature.RevSpeed.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getUsers(){
        return this.userRepository.findAll();
    }



    public User createUser(SignUpRequest signUoRequest){
        User user=new User();

        user.setUserId(UUID.randomUUID().toString());
        user.setFirstName(signUoRequest.getFirstName());
        user.setLastName(signUoRequest.getLastName());
        user.setEmail(signUoRequest.getEmail());
        user.setPhoneNo(signUoRequest.getPhoneNo());
        user.setPassword(passwordEncoder.encode(signUoRequest.getPassword()));
        user.setAddress(signUoRequest.getAddress());
        user.setRole(Role.USER);
        System.out.println(user +"created user");
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found for email: " + email));
    }


    public Boolean isEmailPresent(String mail) {
        Optional<User> user=userRepository.findByEmail(mail);
        if(user.isPresent()){
            return true;
        }else{
            return false;
        }

    }


    public String updateUsersDetails(String id, User user){
        Optional<User> user1= userRepository.findById(String.valueOf(id));

        if(user1.isPresent()){
            User existingUser = user1.get();
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
//            existingUser.setEmail(user.getEmail());
            existingUser.setPhoneNo(user.getPhoneNo());
            existingUser.setAddress(user.getAddress());
            userRepository.save(existingUser);

        }

        return "user details updated";
    }

    public void deletUser(String id){
         userRepository.deleteByEmail(id);
    }

    public String updatePassword(String id,String password){
        System.out.println("inside service");

        Optional<User> user =userRepository.findById(id);
        if(user.isPresent()){
            User user1=user.get();
            System.out.println(password);
            user1.setPassword(passwordEncoder.encode(password));
            userRepository.save(user1);
        }
        return "passsword updated";
    }


    public void deleteuser(String userId) {
        userRepository.deleteuser(userId);
    }

    public User createBusinessUser(SignUpRequest signUoRequest){
        User user=new User();

        user.setUserId(UUID.randomUUID().toString());
        user.setFirstName(signUoRequest.getFirstName());
        user.setLastName(signUoRequest.getLastName());
        user.setEmail(signUoRequest.getEmail());
        user.setPhoneNo(signUoRequest.getPhoneNo());
        user.setPassword(passwordEncoder.encode(signUoRequest.getPassword()));
        user.setAddress(signUoRequest.getAddress());
        user.setRole(Role.USER);
        user.setBusinessUser(true);
        user.setBroadBandUser(false);
        System.out.println(user +" created user");
        return userRepository.save(user);
    }


}
