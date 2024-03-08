package com.ankush003.jshare.services;

import com.ankush003.jshare.domain.Payment;

import java.util.Optional;

public interface PaymentService {

     Payment create(Payment payment);

     Optional<Payment> findById(String paymentId);

}
