package com.george.server.calendar.controller;

import com.george.server.calendar.dto.JwtResponse;
import com.george.server.calendar.dto.LoginRequest;
import com.george.server.calendar.dto.MessageResponse;
import com.george.server.calendar.dto.RegisterRequest;
import com.george.server.calendar.service.AuthService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.authenticateUser(loginRequest);
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        MessageResponse<?> messageResponse = authService.registerUser(registerRequest);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

}
