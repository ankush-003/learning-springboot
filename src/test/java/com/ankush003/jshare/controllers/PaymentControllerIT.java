package com.ankush003.jshare.controllers;

import com.ankush003.jshare.domain.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.ankush003.jshare.TestData.testPayment;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class PaymentControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testThatPaymentIsCreated() throws Exception {
        final Payment payment = testPayment();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String paymentJson = objectMapper.writeValueAsString(payment);
        mockMvc.perform(MockMvcRequestBuilders.put("/payment/" + payment.getPaymentId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(paymentJson))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.paymentId").value(payment.getPaymentId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(payment.getUserId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.friendIds").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(payment.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(payment.getAmount()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date").value(payment.getDate()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(payment.getStatus()));
    }
}
