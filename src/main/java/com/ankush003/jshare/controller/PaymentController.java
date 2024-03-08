package com.ankush003.jshare.controller;

import com.ankush003.jshare.domain.Payment;
import com.ankush003.jshare.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PutMapping("/payment/{paymentId}")
    public ResponseEntity<Payment> createPayment(@PathVariable final String paymentId, @RequestBody final Payment payment) {
        payment.setPaymentId(paymentId);
        final Payment savedPayment = paymentService.create(payment);
        return new ResponseEntity<Payment>(savedPayment, HttpStatus.CREATED);
    }
}
