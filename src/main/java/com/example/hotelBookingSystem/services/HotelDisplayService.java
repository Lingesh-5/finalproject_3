package com.example.hotelBookingSystem.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.hotelBookingSystem.models.Booking;
import com.example.hotelBookingSystem.models.Hotel;
import com.example.hotelBookingSystem.pojos.BookingPayload;
import com.example.hotelBookingSystem.repositories.BookingRepository;
import com.example.hotelBookingSystem.repositories.HotelRepository;

@Service
public class HotelDisplayService {

    @Autowired private HotelRepository hotelRepository;
    @Autowired private BookingRepository bookingRepository;

    public ResponseEntity<?> getAllHotelAndBooking() {
        List<Hotel> hotels = hotelRepository.findAll();
        List<Booking> bookings = bookingRepository.findByStatus("CONFIRMED");
        return ResponseEntity.ok(Map.of("hotels", hotels, "bookings", bookings));
    }

    public void saveBooking(BookingPayload payload) {
        if(payload.getBookingId() == null) {
            Booking booking = new Booking();
            booking.setDateFrom(payload.getFromDate());
            booking.setDateTo(payload.getToDate());
            booking.setPersons(payload.getPersons());
            booking.setUserId(payload.getUserId());
            booking.setHotelId(payload.getHotelId());
            booking.setStatus(payload.getStatus());
            booking.setRooms(payload.getRooms());
            bookingRepository.save(booking);
        }else {
            Booking booking = bookingRepository.findById(payload.getBookingId()).get();
            booking.setStatus(payload.getStatus());
            bookingRepository.save(booking);
        }
        
    }

    public ResponseEntity<?> getBookingAndHotel(Long userId) {
        List<Booking> booking = bookingRepository.findByUserIdAndStatus(userId, "BOOKED");
        Hotel hotel = hotelRepository.findById(booking.get(booking.size()-1).getHotelId()).get();
        return ResponseEntity.ok(Map.of("booking", booking.get(booking.size()-1), "hotel", hotel));
    }

    public ResponseEntity<?> getBooking(Long userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        return ResponseEntity.ok(Map.of("bookings", bookings));
    }
    
}
