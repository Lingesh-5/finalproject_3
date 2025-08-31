package com.example.hotelBookingSystem.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@EnableMethodSecurity
public class HotelDisplayWebController {

    @GetMapping("/hotel-list")
    @PreAuthorize("hasRole('USER')")
    public String hotelList() {
        return "hotel-list.html";
    }
    
    @GetMapping("/payment")
    @PreAuthorize("hasRole('USER')")
    public String payment() {
        return "payment.html";
    }

    @GetMapping("/userBooking-list")
    @PreAuthorize("hasRole('USER')")
    public String userBookingList() {
        return "userBooking-list.html";
    }
}
