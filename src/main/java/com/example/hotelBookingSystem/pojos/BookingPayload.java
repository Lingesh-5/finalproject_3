package com.example.hotelBookingSystem.pojos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingPayload {
    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer persons;
    private Long userId;
    private Long hotelId;
    private String status;
    private Long bookingId;
    private Integer rooms;
}
