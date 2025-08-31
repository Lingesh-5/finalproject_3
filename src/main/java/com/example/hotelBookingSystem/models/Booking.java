package com.example.hotelBookingSystem.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private LocalDate dateFrom;
    @Column(nullable=false)
    private LocalDate dateTo;
    @Column(nullable=false)
    private Integer persons;
    @Column(nullable=false)
    private Long userId;
    @Column(nullable=false)
    private Long hotelId;
    @Column(nullable=false)
    private String status;
    @Column(nullable=false)
    private Integer rooms;
    
}
