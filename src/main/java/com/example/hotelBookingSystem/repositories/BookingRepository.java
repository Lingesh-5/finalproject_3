package com.example.hotelBookingSystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotelBookingSystem.models.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserIdAndStatus(Long id, String status);
    List<Booking> findByUserId(Long id);
    List<Booking> findByStatus(String status);
}
