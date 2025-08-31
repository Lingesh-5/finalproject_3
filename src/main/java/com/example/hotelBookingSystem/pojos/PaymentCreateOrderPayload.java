package com.example.hotelBookingSystem.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCreateOrderPayload {
    private Long amountInRupees;
    private String userId;
}
