package com.example.demo.controller;

import com.example.demo.dto.AuthenticationRequest;
import com.example.demo.dto.AuthenticationResponse;
import com.example.demo.model.User;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          CustomUserDetailsService userDetailsService,
                          JwtUtil jwtUtil,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/public/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        logger.info("Authentication request for user: {}", authenticationRequest.getUsername());
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            logger.info("Authentication successful for user: {}", authenticationRequest.getUsername());
        } catch (Exception e) {
            logger.error("Authentication failed for user: {}. Error: {}", authenticationRequest.getUsername(), e.getMessage());
            return ResponseEntity.badRequest().body("Incorrect username or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        logger.info("JWT token generated for user: {}", authenticationRequest.getUsername());

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/public/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.registerNewUser(user);
            final UserDetails userDetails = userDetailsService.loadUserByUsername(registeredUser.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails.getUsername());
            logger.info("Registered and authenticated user: {}", registeredUser.getUsername());
            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        } catch (Exception e) {
            logger.error("Registration failed for user: {}. Error: {}", user.getUsername(), e.getMessage());
            return ResponseEntity.badRequest().body("Registration error: " + e.getMessage());
        }
    }
}
