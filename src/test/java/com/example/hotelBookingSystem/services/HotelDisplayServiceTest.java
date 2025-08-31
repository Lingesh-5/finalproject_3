package com.example.hotelBookingSystem.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.example.hotelBookingSystem.models.Booking;
import com.example.hotelBookingSystem.models.Hotel;
import com.example.hotelBookingSystem.pojos.BookingPayload;
import com.example.hotelBookingSystem.repositories.BookingRepository;
import com.example.hotelBookingSystem.repositories.HotelRepository;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings("unchecked")
public class HotelDisplayServiceTest {

    @Mock
    private HotelRepository hotelRepository;
    @InjectMocks
    private HotelDisplayService hotelDisplayService;
    @Mock
    private BookingRepository bookingRepository;

    @Test
    public void testGetAllHotelAndBooking() {
        // Arrange
        Hotel mockHotel1 = new Hotel(1L, "test", "test", "test", "test", "test", 123, 2);
        Hotel mockHotel2 = new Hotel(2L, "test", "test", "test", "test", "test", 1234, 2);
        List<Hotel> mockHotels = List.of(mockHotel1, mockHotel2);

        Booking mockBooking1 = new Booking(1L, LocalDate.now(), LocalDate.now(), 1, 1234L, 123L, "CONFIRMED", 2);
        Booking mockBooking2 = new Booking(2L, LocalDate.now(), LocalDate.now(), 1, 1234L, 123L, "CONFIRMED", 2);
        List<Booking> mockBookings = List.of(mockBooking1, mockBooking2);

        Mockito.when(hotelRepository.findAll()).thenReturn(mockHotels);
        Mockito.when(bookingRepository.findByStatus("CONFIRMED")).thenReturn(mockBookings);
        // Act
        ResponseEntity<?> result = hotelDisplayService.getAllHotelAndBooking();
        // Assert 
        Map<String, Object> body = (Map<String, Object>) result.getBody();
        assertNotNull(body);
        List<Hotel> hotels = (List<Hotel>) body.get("hotels");
        List<Booking> bookings = (List<Booking>) body.get("bookings");
        assertEquals(2, hotels.size());
        assertEquals("test", hotels.get(0).getName());
        assertEquals(2, bookings.size());
        assertEquals(1, bookings.get(0).getPersons());
        Mockito.verify(hotelRepository, Mockito.times(1)).findAll();
        Mockito.verify(bookingRepository, Mockito.times(1)).findByStatus("CONFIRMED");
    }

    @Test
    public void testSaveBooking() {
        // Arrange
        BookingPayload payload = new BookingPayload();
        payload.setBookingId(null);
        payload.setFromDate(LocalDate.now());
        payload.setToDate(LocalDate.now());
        payload.setPersons(2);
        payload.setUserId(1L);
        payload.setHotelId(12L);
        payload.setStatus("test");
        payload.setRooms(2);

        // Act
        hotelDisplayService.saveBooking(payload);

        // Assert
        ArgumentCaptor<Booking> bookingCaptor = ArgumentCaptor.forClass(Booking.class);
        verify(bookingRepository, times(1)).save(bookingCaptor.capture());

        Booking savedBooking = bookingCaptor.getValue();
        assertEquals(payload.getFromDate(), savedBooking.getDateFrom());
        assertEquals(payload.getToDate(), savedBooking.getDateTo());
        assertEquals(payload.getPersons(), savedBooking.getPersons());
        assertEquals(payload.getUserId(), savedBooking.getUserId());
        assertEquals(payload.getHotelId(), savedBooking.getHotelId());
        assertEquals(payload.getStatus(), savedBooking.getStatus());
        assertEquals(payload.getRooms(), savedBooking.getRooms());
    }

    @Test
    public void testGetBookingAndHotel() {
        // Arrange
        Booking mockBooking = new Booking(1L, LocalDate.now(), LocalDate.now(), 1, 1234L, 1L, "CONFIRMED", 2);
        Hotel mockHotel = new Hotel(1L, "test", "test", "test", "test", "test", 123, 2);

        Mockito.when(bookingRepository.findByUserIdAndStatus(1L, "BOOKED")).thenReturn(List.of(mockBooking));
        Mockito.when(hotelRepository.findById(1L)).thenReturn(Optional.of(mockHotel));
        // Act
        ResponseEntity<?> result = hotelDisplayService.getBookingAndHotel(1L);
        // Assert 
        Map<String, Object> body = (Map<String, Object>) result.getBody();
        assertNotNull(body);
        Booking booking = (Booking) body.get("booking");
        Hotel hotel = (Hotel) body.get("hotel");
        assertEquals("test", hotel.getName());
        assertEquals(1, booking.getPersons());
        Mockito.verify(bookingRepository, Mockito.times(1)).findByUserIdAndStatus(1L, "BOOKED");
        Mockito.verify(hotelRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void testGetBooking() {
        // Arrange
        Booking mockBooking1 = new Booking(1L, LocalDate.now(), LocalDate.now(), 1, 1234L, 123L, "CONFIRMED", 2);
        Booking mockBooking2 = new Booking(1L, LocalDate.now(), LocalDate.now(), 1, 1234L, 123L, "CONFIRMED", 2);
        List<Booking> mockBookings = List.of(mockBooking1, mockBooking2);

        Mockito.when(bookingRepository.findByUserId(1L)).thenReturn(mockBookings);
        // Act
        ResponseEntity<?> result = hotelDisplayService.getBooking(1L);
        // Assert 
        Map<String, Object> body = (Map<String, Object>) result.getBody();
        assertNotNull(body);
        List<Booking> bookings = (List<Booking>) body.get("bookings");
        assertEquals(2, bookings.size());
        assertEquals(1, bookings.get(0).getPersons());
        Mockito.verify(bookingRepository, Mockito.times(1)).findByUserId(1L);
    }
    
}
