package com.example.hotelBookingSystem.models;

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
@Table(name="hotels")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private String city;
    @Column(nullable=false)
    private String address;
    @Column(nullable=false)
    private String description;
    @Column(nullable=false)
    private String rating;
    @Column(nullable=false)
    private Integer price;
    @Column(nullable=false)
    private Integer rooms;;
    
}
