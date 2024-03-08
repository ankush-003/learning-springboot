package com.ankush003.jshare.services;

import com.ankush003.jshare.domain.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

     boolean isBookExists(Payment payment);

     Payment save(Payment payment);

     Optional<Payment> findById(String paymentId);

     List<Payment> listPayments();

     void deleteById(String paymentId);

}
