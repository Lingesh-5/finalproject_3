package com.example.hotelBookingSystem.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.hotelBookingSystem.models.Booking;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookingRepositoryTest {

    @Autowired private BookingRepository bookingRepository;

    @Test
    public void testFindByUserIdAndStatus() {
        // Arrange
        bookingRepository.save(new Booking(null, LocalDate.now(), LocalDate.now(), 1, 1234L, 123L, "test", 2));
        bookingRepository.save(new Booking(null, LocalDate.now(), LocalDate.now(), 2, 1234L, 123L, "test", 2));

        // Act
        List<Booking> result = bookingRepository.findByUserIdAndStatus(1234L, "test");

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testFindByUserId() {
        // Arrange
        bookingRepository.save(new Booking(null, LocalDate.now(), LocalDate.now(), 1, 1234L, 123L, "test", 2));
        bookingRepository.save(new Booking(null, LocalDate.now(), LocalDate.now(), 2, 1234L, 123L, "test", 2));

        // Act
        List<Booking> result = bookingRepository.findByUserId(1234L);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testFindByStatus() {
        // Arrange
        bookingRepository.save(new Booking(null, LocalDate.now(), LocalDate.now(), 1, 1234L, 123L, "test", 2));
        bookingRepository.save(new Booking(null, LocalDate.now(), LocalDate.now(), 2, 1234L, 123L, "test", 2));

        // Act
        List<Booking> result = bookingRepository.findByStatus("test");

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }
    
}
