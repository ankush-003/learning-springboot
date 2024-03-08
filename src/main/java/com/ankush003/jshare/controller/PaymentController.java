package com.ankush003.jshare.controller;

import com.ankush003.jshare.domain.Payment;
import com.ankush003.jshare.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PutMapping("/payment/{paymentId}")
    public ResponseEntity<Payment> createUpdatePayment(@PathVariable final String paymentId, @RequestBody final Payment payment) {
        payment.setPaymentId(paymentId);
        final boolean isPaymentExists = paymentService.isBookExists(payment);
        final Payment savedPayment = paymentService.save(payment);
        return new ResponseEntity<Payment>(savedPayment, isPaymentExists ? HttpStatus.OK : HttpStatus.CREATED);
    }

    @GetMapping("/payment/{paymentId}")
    public ResponseEntity<Payment> getPayment(@PathVariable final String paymentId) {
        final Optional<Payment> foundPayment = paymentService.findById(paymentId);
        return foundPayment.map(payment -> new ResponseEntity<Payment>(payment, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<Payment>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/payment")
    public ResponseEntity<List<Payment>> listPayments() {
        final List<Payment> payments = paymentService.listPayments();
        return new ResponseEntity<List<Payment>>(payments, HttpStatus.OK);
    }

    @DeleteMapping("/payment/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable final String paymentId) {
        paymentService.deleteById(paymentId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
