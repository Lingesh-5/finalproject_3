package com.example.hotelBookingSystem.pojos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelPayload {
    private Long id;
    @NotBlank(message="Please enter name")
    private String name;
    @NotBlank(message="Please enter city")
    private String city;
    @NotBlank(message="Please enter address")
    private String address;
    @NotBlank(message="Please enter description")
    private String description;
    @NotBlank(message="Please enter rating")
    private String rating;
    private Integer price;
    private Integer rooms;
    
}
