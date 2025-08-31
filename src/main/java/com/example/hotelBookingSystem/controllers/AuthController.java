package com.example.hotelBookingSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotelBookingSystem.pojos.LoginPayload;
import com.example.hotelBookingSystem.pojos.UserPayload;
import com.example.hotelBookingSystem.services.AuthService;

@RestController
@RequestMapping("/auth/rest")
public class AuthController {

    @Autowired private AuthService authService;

    @PostMapping("/save-register")
    public ResponseEntity<?> saveRegisterDetails(@RequestBody UserPayload payload) {
        authService.saveRegister(payload);
        return ResponseEntity.ok("Registered successfully");
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> loginVerify(@RequestBody LoginPayload payload) {
        return ResponseEntity.ok(authService.verify(payload));
    }
    
}