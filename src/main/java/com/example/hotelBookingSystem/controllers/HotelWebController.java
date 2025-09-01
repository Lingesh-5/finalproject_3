package com.example.hotelBookingSystem.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@EnableMethodSecurity
public class HotelWebController {

    @GetMapping("/hotel")
    @PreAuthorize("hasRole('ADMIN')")
    public String hotel() {
        return "hotel.html";
    }

    @GetMapping("/booking-list")
    @PreAuthorize("hasRole('ADMIN')")
    public String bookingList() {
        return "booking-list.html";
    }

    @GetMapping("/user-list")
    @PreAuthorize("hasRole('ADMIN')")
    public String userList() {
        return "user-list.html";
    }

    @GetMapping("/payment-list")
    @PreAuthorize("hasRole('ADMIN')")
    public String paymentList() {
        return "payment-list.html";
    }
    
}
