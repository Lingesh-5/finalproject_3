package com.example.hotelBookingSystem.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentVerifyPayload {
    private String razorpayPaymentId;
    private String razorpayOrderId;
    private String razorpaySignature;
}
