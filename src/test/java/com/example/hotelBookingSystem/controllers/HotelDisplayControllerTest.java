package com.example.hotelBookingSystem.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.hotelBookingSystem.services.HotelDisplayService;

@WebMvcTest(HotelDisplayController.class)
@AutoConfigureMockMvc(addFilters=false)
public class HotelDisplayControllerTest {

    @Autowired private MockMvc mockMvc;
    @SuppressWarnings({"removal", "unused"})
    @MockBean private HotelDisplayService hotelDisplayService;

    @Test
    @WithMockUser(username="testUser", roles={"USER"})
    public void testGetAllHotelAndBookingDetails() throws Exception {
    
        mockMvc.perform(get("/user/rest/get-allHotelAndBooking")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="testUser", roles={"USER"})
    public void testSaveBookingDetails() throws Exception {
        String requestBody = """
                        {
                            "fromDate":"2025-08-28",
                            "toDate":"2025-08-28",
                            "persons":1,
                            "userId":123,
                            "hotelId":1234,
                            "status":"test",
                            "bookingId":123
                        }         
                """;
        mockMvc.perform(
            post("/user/rest/save-booking")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
        .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="testUser", roles={"USER"})
    public void testGetBookingAndHotelDetails() throws Exception {
    
        mockMvc.perform(get("/user/rest/get-bookingAndHotel/1")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="testUser", roles={"USER"})
    public void testGetBookingDetails() throws Exception {
    
        mockMvc.perform(get("/user/rest/get-booking/1")).andExpect(status().isOk());
    }
    
}
