package com.example.hotelBookingSystem.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.hotelBookingSystem.services.PaymentService;

@WebMvcTest(PaymentController.class)
@AutoConfigureMockMvc(addFilters=false)
public class PaymentControllerTest {

    @Autowired private MockMvc mockMvc;
    @SuppressWarnings("removal")
    @MockBean private PaymentService paymentService;

    @Test
    @WithMockUser(username="testUser", roles={"USER"})
    public void testCreateOrder() throws Exception {
        String requestBody = """
                        {
                            "amountInRupees":123,
                            "userId":"test"
                        }         
                """;
        mockMvc.perform(
            post("/rest/payments/create-order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
        .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="testUser", roles={"USER"})
    public void testVerifyPayment() throws Exception {
        when(paymentService.verifyPaymentSignature("test", "test", "test"))
                .thenReturn(true);
        String requestBody = """
                        {
                            "razorpayPaymentId":"test",
                            "razorpayOrderId":"test",
                            "razorpaySignature":"test"
                        }         
                """;
        mockMvc.perform(
            post("/rest/payments/verify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
        .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="testUser", roles={"USER"})
    public void testVerifyPaymentBadRequest() throws Exception {
        when(paymentService.verifyPaymentSignature("test1", "test1", "test1"))
                .thenReturn(false);
        String requestBody = """
                        {
                            "razorpayPaymentId":"test1",
                            "razorpayOrderId":"test1",
                            "razorpaySignature":"test1"
                        }         
                """;
        mockMvc.perform(
            post("/rest/payments/verify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
        .andExpect(status().isBadRequest());
    }
    
}
