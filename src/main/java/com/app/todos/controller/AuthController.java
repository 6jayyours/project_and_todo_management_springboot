package com.app.todos.controller;


import com.app.todos.request.AuthenticationRequest;
import com.app.todos.request.RegisterRequest;
import com.app.todos.response.AuthenticationResponse;
import com.app.todos.response.RegisterResponse;
import com.app.todos.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        System.out.println(request.getUsername());
        System.out.println(request.getPassword());
        return userService.registerUser(request);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return userService.authenticateUser(request);
    }


}
