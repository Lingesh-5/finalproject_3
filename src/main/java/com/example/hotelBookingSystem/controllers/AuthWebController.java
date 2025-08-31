package com.example.hotelBookingSystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthWebController {

    @GetMapping("/auth/register")
    public String register() {
        return "register.html";
    }

    @GetMapping()
    public String login() {
        return "index.html";
    }
    
}