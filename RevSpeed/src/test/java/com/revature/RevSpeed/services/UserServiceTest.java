package com.revature.RevSpeed.services;

import com.revature.RevSpeed.dto.SignUpRequest;
import com.revature.RevSpeed.models.Role;
import com.revature.RevSpeed.models.User;
import com.revature.RevSpeed.repositorys.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@AutoConfigureMockMvc
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUsers_ReturnsListOfUsers() {
        // Arrange
        Mockito.when(userRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        var result = userService.getUsers();

        // Assert
        assertEquals(0, result.size());
    }

    @Test
    void createUser_ReturnsCreatedUser() {
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
        mockUser.setRole(Role.USER);

        Mockito.when(userRepository.save(any(User.class))).thenReturn(mockUser);
        Mockito.when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        // Act
        var result = userService.createUser(signUpRequest);

        // Assert
        assertEquals(mockUser, result);
        Mockito.verify(userRepository, Mockito.times(1)).save(any(User.class));
    }

}