package com.app.todos.service;

import com.app.todos.entity.User;
import com.app.todos.exceptions.RegistrationException;
import com.app.todos.exceptions.UserNotFoundException;
import com.app.todos.repository.UserRepository;
import com.app.todos.request.AuthenticationRequest;
import com.app.todos.request.RegisterRequest;
import com.app.todos.response.AuthenticationResponse;
import com.app.todos.response.RegisterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService  {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }


    public ResponseEntity<RegisterResponse> registerUser(RegisterRequest request) {
        try {
            if (userRepository.findByUsername(request.getUsername()).isPresent()) {
                throw new RegistrationException("Username is already taken");
            }

            User user = new User();
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            userRepository.save(user);

            return ResponseEntity.ok(new RegisterResponse("User registered successfully"));
        } catch (RegistrationException ex) {
            // Handle specific registration exception
            return ResponseEntity.badRequest().body(new RegisterResponse(ex.getMessage()));
        } catch (Exception ex) {
            // Handle any other unexpected exceptions
            return ResponseEntity.status(500).body(new RegisterResponse("Internal server error: " + ex.getMessage()));
        }
    }

    public ResponseEntity<AuthenticationResponse> authenticateUser(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            Optional<User> user = userRepository.findByUsername(request.getUsername());

            return ResponseEntity.ok(new AuthenticationResponse("User login successful",user.get().getId()));
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(401).body(new AuthenticationResponse("Invalid username or password",null));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(new AuthenticationResponse("Internal server error: " + ex.getMessage(),null));
        }
    }

}
