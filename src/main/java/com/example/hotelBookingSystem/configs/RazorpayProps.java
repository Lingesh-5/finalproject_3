package com.example.hotelBookingSystem.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "razorpay")
@Data
public class RazorpayProps {
    private String keyId;
    private String secret;
}
