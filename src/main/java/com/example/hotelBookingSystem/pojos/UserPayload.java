package com.example.hotelBookingSystem.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPayload {
    private String name;
    private String email;
    private String password;
    private String role;
    private Long userId;
}
