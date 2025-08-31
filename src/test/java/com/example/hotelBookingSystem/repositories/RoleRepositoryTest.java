package com.example.hotelBookingSystem.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.hotelBookingSystem.models.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoleRepositoryTest {

    @Autowired RoleRepository roleRepository;

    @Test
    public void testFindByName() {
        // Arrange
        Role role = new Role(null, "test");
        roleRepository.save(role);

        // Act
        Set<Role> result = roleRepository.findByName("test");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
    }
    
}
