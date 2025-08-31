package com.example.hotelBookingSystem.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.hotelBookingSystem.models.Booking;
import com.example.hotelBookingSystem.models.Hotel;
import com.example.hotelBookingSystem.models.Payment;
import com.example.hotelBookingSystem.models.User;
import com.example.hotelBookingSystem.pojos.HotelPayload;
import com.example.hotelBookingSystem.repositories.BookingRepository;
import com.example.hotelBookingSystem.repositories.HotelRepository;
import com.example.hotelBookingSystem.repositories.PaymentRepository;
import com.example.hotelBookingSystem.repositories.UserRepository;

@Service
public class HotelService {

    @Autowired private HotelRepository hotelRepository;
    @Autowired private BookingRepository bookingRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private PaymentRepository paymentRepository;

    public void saveHotel(HotelPayload payload) {
        if(payload.getId() == null) {
            Hotel hotel = new Hotel();
            hotel.setName(payload.getName());
            hotel.setCity(payload.getCity());
            hotel.setAddress(payload.getAddress());
            hotel.setDescription(payload.getDescription());
            hotel.setRating(payload.getRating());
            hotel.setPrice(payload.getPrice());
            hotel.setRooms(payload.getRooms());
            hotelRepository.save(hotel);
        }else {
            Hotel hotel = hotelRepository.findById(payload.getId()).get();
            hotel.setName(payload.getName());
            hotel.setCity(payload.getCity());
            hotel.setAddress(payload.getAddress());
            hotel.setDescription(payload.getDescription());
            hotel.setRating(payload.getRating());
            hotel.setPrice(payload.getPrice());
            hotel.setRooms(payload.getRooms());
            hotelRepository.save(hotel);
        }
    }

    public ResponseEntity<?> getAllHotel() {
        List<Hotel> hotels = hotelRepository.findAll();
        return ResponseEntity.ok(Map.of("hotels", hotels));
    }

    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

    public ResponseEntity<?> getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id).get();
        return ResponseEntity.ok(Map.of("hotel", hotel));
    }

    public ResponseEntity<?> getAllBooking() {
        List<Booking> bookings = bookingRepository.findAll();
        return ResponseEntity.ok(Map.of("bookings", bookings));
    }

    public ResponseEntity<?> getAllUser() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(Map.of("users", users));
    }

    public ResponseEntity<?> getAllPayment() {
        List<Payment> payments = paymentRepository.findAll();
        return ResponseEntity.ok(Map.of("payments", payments));
    }
    
}
