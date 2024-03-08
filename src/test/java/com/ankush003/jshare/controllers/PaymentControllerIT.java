package com.ankush003.jshare.controllers;

import com.ankush003.jshare.domain.Payment;
import com.ankush003.jshare.services.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.ankush003.jshare.TestData.testPayment;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PaymentControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PaymentService paymentService;

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

    @Test
    public void testGetPaymentWhenPaymentNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/payment/1234567"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetPaymentWhenPaymentIsFound() throws Exception {
        final Payment payment = testPayment();
        paymentService.save(payment);
        mockMvc.perform(MockMvcRequestBuilders.get("/payment/" + payment.getPaymentId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.paymentId").value(payment.getPaymentId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(payment.getUserId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.friendIds").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(payment.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(payment.getAmount()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date").value(payment.getDate()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(payment.getStatus()));
    }

    @Test
    public void testListPaymentsEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/payment"))
                .andExpect(status().isOk());
    }

    @Test
    public void testListPayments() throws Exception {
        final Payment payment = testPayment();
        paymentService.save(payment);
        mockMvc.perform(MockMvcRequestBuilders.get("/payment"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].paymentId").value(payment.getPaymentId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId").value(payment.getUserId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].friendIds").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value(payment.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].amount").value(payment.getAmount()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].date").value(payment.getDate()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].status").value(payment.getStatus()));
    }

    @Test
    public void testThatPaymentIsUpdated() throws Exception {
        final Payment payment = testPayment();
        paymentService.save(payment);
        payment.setStatus("COMPLETED");
//        paymentService.save(payment);
        final ObjectMapper objectMapper = new ObjectMapper();
        final String paymentJson = objectMapper.writeValueAsString(payment);
        mockMvc.perform(MockMvcRequestBuilders.put("/payment/" + payment.getPaymentId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(paymentJson))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.paymentId").value(payment.getPaymentId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(payment.getUserId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.friendIds").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(payment.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(payment.getAmount()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date").value(payment.getDate()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(payment.getStatus()));
    }

    @Test
    public void testDeleteByIdWhenPaymentNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/payment/1234567"))
                .andExpect(status().isNoContent());
    }
}
