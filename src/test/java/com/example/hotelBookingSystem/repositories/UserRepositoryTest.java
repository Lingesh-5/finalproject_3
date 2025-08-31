package com.example.hotelBookingSystem.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.hotelBookingSystem.models.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired private UserRepository userRepository;

    @Test
    public void testFindByEmail() {
        // Arrange
        User user = new User(null, "test", "test100@mail.com", "test", null);
        userRepository.save(user);

        // Act
        User result = userRepository.findByEmail("test100@mail.com");

        // Assert
        assertNotNull(result);
        assertEquals("test", result.getName());
    }
    
}
