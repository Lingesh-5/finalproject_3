package com.example.hotelBookingSystem.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.hotelBookingSystem.services.HotelService;

@WebMvcTest(HotelController.class)
@AutoConfigureMockMvc(addFilters=false)
public class HotelControllerTest {

    @Autowired private MockMvc mockMvc;
    @SuppressWarnings({"removal", "unused"})
    @MockBean private HotelService hotelService;

    @Test
    @WithMockUser(username="testUser", roles={"ADMIN"})
    public void testSaveHotelDetails() throws Exception {
        String requestBody = """
                        {
                            "name":"test",
                            "city":"test",
                            "address":"test",
                            "description":"test",
                            "rating":"test",
                            "price":123,
                            "id":123
                        }         
                """;
        mockMvc.perform(
            post("/admin/rest/save-hotel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
        .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="testUser", roles={"ADMIN"})
    public void testGetAllHotelDetails() throws Exception {
    
        mockMvc.perform(get("/admin/rest/get-hotel")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="testUser", roles={"ADMIN"})
    public void testDeleteHotelDetails() throws Exception {
    
        mockMvc.perform(delete("/admin/rest/delete-hotel/1")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="testUser", roles={"ADMIN"})
    public void testGetHotelDetailsById() throws Exception {
    
        mockMvc.perform(get("/admin/rest/get-hotelById/1")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="testUser", roles={"ADMIN"})
    public void testGetAllBookingDetails() throws Exception {
    
        mockMvc.perform(get("/admin/rest/get-allBooking")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="testUser", roles={"ADMIN"})
    public void testGetAllUserDetails() throws Exception {
    
        mockMvc.perform(get("/admin/rest/get-allUser")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="testUser", roles={"ADMIN"})
    public void testGetAllPaymentDetails() throws Exception {
    
        mockMvc.perform(get("/admin/rest/get-allPayment")).andExpect(status().isOk());
    }
    
}
