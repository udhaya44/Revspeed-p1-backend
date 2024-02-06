package com.revature.RevSpeed.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.RevSpeed.dto.SignUpRequest;
import com.revature.RevSpeed.models.JwtRequest;
import com.revature.RevSpeed.models.User;
import com.revature.RevSpeed.security.JWTHelper;
import com.revature.RevSpeed.services.EmailService;
import com.revature.RevSpeed.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class UserContollerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailService
            emailService;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JWTHelper jwtHelper;

    @MockBean
    private UserService userService;

    @InjectMocks
    private UserServiceLinkController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_ReturnsCreatedUser() throws Exception {
        // Arrange
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setFirstName("John");
        signUpRequest.setLastName("Doe");
        signUpRequest.setEmail("john.doe@example.com");
        signUpRequest.setPhoneNo("1234567890");
        signUpRequest.setPassword("password");
        signUpRequest.setAddress("123 Main St");

     User mockUser = new User();
        mockUser.setUserId(UUID.randomUUID().toString());
        mockUser.setFirstName(signUpRequest.getFirstName());
        mockUser.setLastName(signUpRequest.getLastName());
        mockUser.setEmail(signUpRequest.getEmail());
        mockUser.setPhoneNo(signUpRequest.getPhoneNo());
        mockUser.setPassword("encodedPassword");
        mockUser.setAddress(signUpRequest.getAddress());

        when(userService.createUser(any(SignUpRequest.class))).thenReturn(mockUser);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/user/create-user")
                        .content(asJsonString(signUpRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").exists());
    }

    @Test
    void getUser_ReturnsListOfUsers() throws Exception {
        // Arrange
        when(userService.getUsers()).thenReturn(Collections.emptyList());

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/user/getAllUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0));
    }

    @Test
    void getUserDetails_ReturnsUserDetails() throws Exception {
        // Arrange
        User mockUser = new User();
        mockUser.setUserId(UUID.randomUUID().toString());
        mockUser.setFirstName("John");
        mockUser.setLastName("Doe");
        mockUser.setEmail("john.doe@example.com");
        mockUser.setPhoneNo("1234567890");
        mockUser.setPassword("encodedPassword");
        mockUser.setAddress("123 Main St");

        when(userService.getUserByEmail("john.doe@example.com")).thenReturn(mockUser);
        when(jwtHelper.getUsernameFromToken(anyString())).thenReturn("john.doe@example.com");

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/user/getAUser")
                        .header("Authorization", "Bearer mockToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(mockUser.getUserId()));
    }

//    @Test
//    void login_ReturnsJwtResponse() throws Exception {
//        // Arrange
//        JwtRequest jwtRequest = new JwtRequest("john.doe@example.com", "password");
//
//        when(userDetailsService.loadUserByUsername("john.doe@example.com")).thenReturn(null);
//        when(authenticationManager.authenticate(any())).thenReturn(null);
//        when(jwtHelper.generateToken(any())).thenReturn("mockToken");
//
//        // Act and Assert
//        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
//                        .content(asJsonString(jwtRequest))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.jwtToken").value("mockToken"));
//    }

    // Add test cases for other methods in UserController as needed

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}