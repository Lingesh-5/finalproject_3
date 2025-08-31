package com.example.hotelBookingSystem.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.hotelBookingSystem.models.Role;
import com.example.hotelBookingSystem.models.User;
import com.example.hotelBookingSystem.pojos.UserPayload;
import com.example.hotelBookingSystem.repositories.RoleRepository;
import com.example.hotelBookingSystem.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private AuthService authService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RoleRepository roleRepository;

    @Test
    public void testSaveRegister() {
        // Arrange
        UserPayload payload = new UserPayload();
        payload.setName("test");
        payload.setEmail("test@mail.com");
        payload.setPassword(passwordEncoder.encode("test"));
        payload.setRole("test");

        Role mockRole = new Role(1L, "test");

        Mockito.when(roleRepository.findByName("test")).thenReturn(Set.of(mockRole));
        // Act
        authService.saveRegister(payload);

        // Assert
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        assertEquals(payload.getName(), savedUser.getName());
        assertEquals(payload.getEmail(), savedUser.getEmail());
        assertEquals(payload.getPassword(), savedUser.getPassword());
    }
    
}
