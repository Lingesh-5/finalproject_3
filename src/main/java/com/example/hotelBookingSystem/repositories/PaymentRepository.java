package com.example.hotelBookingSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.hotelBookingSystem.models.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Transactional
    @Modifying
    @Query("update Payment p set p.status='SUCCESS', p.paymentId=:paymentId where p.bookingId=:orderId and p.status<>'SUCCESS'")
    void markPaid(String orderId, String paymentId);

}
