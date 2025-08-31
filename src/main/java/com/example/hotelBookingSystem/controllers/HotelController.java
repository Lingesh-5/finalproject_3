package com.example.hotelBookingSystem.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotelBookingSystem.pojos.HotelPayload;
import com.example.hotelBookingSystem.services.HotelService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/rest")
@EnableMethodSecurity
@Validated
public class HotelController {

    @Autowired private HotelService hotelService;

    @PostMapping("/save-hotel")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveHotelDetails(@Valid @RequestBody HotelPayload payload) {
        hotelService.saveHotel(payload);
        return ResponseEntity.ok(Map.of("status", "Saved successfully"));
    }

    @GetMapping("/get-hotel")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllHotelDetails() {
        return ResponseEntity.ok(hotelService.getAllHotel());
    }

    @DeleteMapping("/delete-hotel/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteHotelDetails(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @GetMapping("/get-hotelById/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getHotelDetailsById(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @GetMapping("/get-allBooking")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllBookingDetails() {
        return ResponseEntity.ok(hotelService.getAllBooking());
    }
    
    @GetMapping("/get-allUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUserDetails() {
        return ResponseEntity.ok(hotelService.getAllUser());
    }

    @GetMapping("/get-allPayment")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllPaymentDetails() {
        return ResponseEntity.ok(hotelService.getAllPayment());
    }
    
}
