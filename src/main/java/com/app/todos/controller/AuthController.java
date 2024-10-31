package com.app.todos.controller;

import com.app.todos.request.AuthenticationRequest;
import com.app.todos.request.RegisterRequest;
import com.app.todos.response.AuthenticationResponse;
import com.app.todos.response.RegisterResponse;
import com.app.todos.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Controller for handling authentication-related requests, including user registration and login.
 */
@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;

    /**
     * Constructor for dependency injection of UserService.
     */
    public AuthController(UserService userService) {
        this.userService = userService;
    }


    /**
     * Endpoint for registering a new user.
     */
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        return userService.registerUser(request);
    }

    /**
     * Endpoint for authenticating a user (login).
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return userService.authenticateUser(request);
    }




}
