package com.example.hotelBookingSystem.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.hotelBookingSystem.models.Payment;

import jakarta.transaction.Transactional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PaymentRepositoryTest {

    @Autowired private PaymentRepository paymentRepository;

    @Test
    @Transactional
    public void testMarkPaid() {
        // given
        Payment payment = new Payment(null, "test123", 100L, "test", "test123", "test123", null);
        paymentRepository.save(payment);

        // when
        paymentRepository.markPaid("test123", "test123");

        // then
        Optional<Payment> updated = paymentRepository.findById(payment.getId());
        assertNotNull(updated);
        assertEquals("test123", updated.get().getBookingId());
    }
    
}
