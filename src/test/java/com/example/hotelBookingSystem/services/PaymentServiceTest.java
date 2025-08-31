package com.example.hotelBookingSystem.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HexFormat;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.hotelBookingSystem.configs.RazorpayProps;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private RazorpayProps props;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    void testVerifyPaymentSignature_ValidSignature() throws Exception {
        // given
        String orderId = "test123";
        String paymentId = "test123";
        String secret = "testsecret";

        String data = orderId + "|" + paymentId;

        // Generate expected signature using same algo
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secretKey);
        String expectedSignature = HexFormat.of().formatHex(sha256_HMAC.doFinal(data.getBytes()));

        when(props.getSecret()).thenReturn(secret);

        // when
        boolean result = paymentService.verifyPaymentSignature(orderId, paymentId, expectedSignature);

        // then
        assertTrue(result, "Signature should be valid");
    }

    @Test
    void testVerifyPaymentSignature_InvalidSignature() {
        // given
        String orderId = "test123";
        String paymentId = "test123";
        when(props.getSecret()).thenReturn("testsecret");

        // wrong signature
        String invalidSignature = "123456abcdef";

        // when
        boolean result = paymentService.verifyPaymentSignature(orderId, paymentId, invalidSignature);

        // then
        assertFalse(result, "Signature should be invalid");
    }

}

