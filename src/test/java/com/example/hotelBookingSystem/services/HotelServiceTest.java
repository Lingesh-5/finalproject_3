package com.example.hotelBookingSystem.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.example.hotelBookingSystem.models.Booking;
import com.example.hotelBookingSystem.models.Hotel;
import com.example.hotelBookingSystem.models.Payment;
import com.example.hotelBookingSystem.models.User;
import com.example.hotelBookingSystem.pojos.HotelPayload;
import com.example.hotelBookingSystem.repositories.BookingRepository;
import com.example.hotelBookingSystem.repositories.HotelRepository;
import com.example.hotelBookingSystem.repositories.PaymentRepository;
import com.example.hotelBookingSystem.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings("unchecked")
public class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;
    @InjectMocks
    private HotelService hotelService;
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PaymentRepository paymentRepository;

    @Test
    public void testSaveHotel() {
        // Arrange
        HotelPayload payload = new HotelPayload();
        payload.setName("test");
        payload.setCity("test");
        payload.setAddress("test");
        payload.setDescription("test");
        payload.setRating("5");
        payload.setPrice(1200);
        payload.setRooms(2);

        // Act
        hotelService.saveHotel(payload);

        // Assert
        ArgumentCaptor<Hotel> hotelCaptor = ArgumentCaptor.forClass(Hotel.class);
        verify(hotelRepository, times(1)).save(hotelCaptor.capture());

        Hotel savedHotel = hotelCaptor.getValue();
        assertEquals(payload.getName(), savedHotel.getName());
        assertEquals(payload.getCity(), savedHotel.getCity());
        assertEquals(payload.getAddress(), savedHotel.getAddress());
        assertEquals(payload.getDescription(), savedHotel.getDescription());
        assertEquals(payload.getRating(), savedHotel.getRating());
        assertEquals(payload.getPrice(), savedHotel.getPrice());
        assertEquals(payload.getRooms(), savedHotel.getRooms());
    }

    @Test
    public void testGetAllHotel() {
        // Arrange
        Hotel mockHotel1 = new Hotel(1L, "test", "test", "test", "test", "test", 123, 2);
        Hotel mockHotel2 = new Hotel(2L, "test", "test", "test", "test", "test", 1234, 2);
        List<Hotel> mockHotels = List.of(mockHotel1, mockHotel2);

        Mockito.when(hotelRepository.findAll()).thenReturn(mockHotels);
        // Act
        ResponseEntity<?> result = hotelService.getAllHotel();
        // Assert 
        Map<String, Object> body = (Map<String, Object>) result.getBody();
        assertNotNull(body);
        List<Hotel> hotels = (List<Hotel>) body.get("hotels");
        assertEquals(2, hotels.size());
        assertEquals("test", hotels.get(0).getName());
        Mockito.verify(hotelRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testDeleteHotel() {
        // Arrange
        Long hotelId = 10L;

        // Act
        hotelService.deleteHotel(hotelId);

        // Assert
        verify(hotelRepository, times(1)).deleteById(hotelId);
    }

    @Test
    public void testGetHotelById() {
        // Arrange
        Hotel mockHotel = new Hotel(1L, "test", "test", "test", "test", "test", 123, 2);

        Mockito.when(hotelRepository.findById(1L)).thenReturn(Optional.of(mockHotel));
        // Act
        ResponseEntity<?> result = hotelService.getHotelById(1L);
        // Assert 
        Map<String, Object> body = (Map<String, Object>) result.getBody();
        assertNotNull(body);
        Hotel hotel = (Hotel) body.get("hotel");
        assertEquals("test", hotel.getName());
        Mockito.verify(hotelRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void testGetAllBooking() {
        // Arrange
        Booking mockBooking1 = new Booking(1L, LocalDate.now(), LocalDate.now(), 1, 1234L, 123L, "CONFIRMED", 2);
        Booking mockBooking2 = new Booking(1L, LocalDate.now(), LocalDate.now(), 1, 1234L, 123L, "CONFIRMED", 2);
        List<Booking> mockBookings = List.of(mockBooking1, mockBooking2);

        Mockito.when(bookingRepository.findAll()).thenReturn(mockBookings);
        // Act
        ResponseEntity<?> result = hotelService.getAllBooking();
        // Assert 
        Map<String, Object> body = (Map<String, Object>) result.getBody();
        assertNotNull(body);
        List<Booking> bookings = (List<Booking>) body.get("bookings");
        assertEquals(2, bookings.size());
        assertEquals(1, bookings.get(0).getPersons());
        Mockito.verify(bookingRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testGetAllUser() {
        // Arrange
        User mockUser1 = new User(1L, "test", "test@mail.com", "test", null);
        User mockUser2 = new User(2L, "test1", "test1@mail.com", "test1", null);
        List<User> mockUsers = List.of(mockUser1, mockUser2);

        Mockito.when(userRepository.findAll()).thenReturn(mockUsers);
        // Act
        ResponseEntity<?> result = hotelService.getAllUser();
        // Assert 
        Map<String, Object> body = (Map<String, Object>) result.getBody();
        assertNotNull(body);
        List<User> users = (List<User>) body.get("users");
        assertEquals(2, users.size());
        assertEquals("test", users.get(0).getName());
        Mockito.verify(userRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testGetAllPayment() {
        // Arrange
        Payment mockPayment1 = new Payment(1L, "test123", 1200L, "test", "test123", "test123", LocalDateTime.now());
        Payment mockPayment2 = new Payment(2L, "test123", 1500L, "test", "test123", "test123", LocalDateTime.now());
        List<Payment> mockPayments = List.of(mockPayment1, mockPayment2);

        Mockito.when(paymentRepository.findAll()).thenReturn(mockPayments);
        // Act
        ResponseEntity<?> result = hotelService.getAllPayment();
        // Assert 
        Map<String, Object> body = (Map<String, Object>) result.getBody();
        assertNotNull(body);
        List<Payment> payments = (List<Payment>) body.get("payments");
        assertEquals(2, payments.size());
        assertEquals(1200L, payments.get(0).getAmountInRupees());
        Mockito.verify(paymentRepository, Mockito.times(1)).findAll();
    }
    
}
