package com.example.hotelBookingSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotelBookingSystem.pojos.BookingPayload;
import com.example.hotelBookingSystem.services.HotelDisplayService;

@RestController
@RequestMapping("/user/rest")
@EnableMethodSecurity
public class HotelDisplayController {

    @Autowired private HotelDisplayService hotelDisplayService;

    @GetMapping("/get-allHotelAndBooking")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getAllHotelAndBookingDetails() {
        return ResponseEntity.ok(hotelDisplayService.getAllHotelAndBooking());
    }

    @PostMapping("/save-booking")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> saveBookingDetails(@RequestBody BookingPayload payload) {
        hotelDisplayService.saveBooking(payload);
        return ResponseEntity.ok("Booked successfully");
    }

    @GetMapping("/get-bookingAndHotel/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getBookingAndHotelDetails(@PathVariable Long userId) {
        return ResponseEntity.ok(hotelDisplayService.getBookingAndHotel(userId));
    }

    @GetMapping("/get-booking/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getBookingDetails(@PathVariable Long userId) {
        return ResponseEntity.ok(hotelDisplayService.getBooking(userId));
    }
    

}
