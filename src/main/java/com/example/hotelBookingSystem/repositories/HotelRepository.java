package com.example.hotelBookingSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotelBookingSystem.models.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    
}
