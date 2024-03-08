package com.ankush003.jshare.services.impl;

import com.ankush003.jshare.domain.Payment;
import com.ankush003.jshare.domain.PaymentEntity;
import com.ankush003.jshare.repositories.PaymentRepository;
import com.ankush003.jshare.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
    @Override
    public Payment create(Payment payment) {
        final PaymentEntity paymentEntity = paymentToPaymentEntity(payment);
        final PaymentEntity savedPaymentEntity =  paymentRepository.save(paymentEntity);
        return paymentEntityToPayment(savedPaymentEntity);
    }

    private PaymentEntity paymentToPaymentEntity(Payment payment) {
        return PaymentEntity.builder()
                .paymentId(payment.getPaymentId())
                .userId(payment.getUserId())
                .friendIds(payment.getFriendIds())
                .description(payment.getDescription())
                .amount(payment.getAmount())
                .date(payment.getDate())
                .status(payment.getStatus())
                .build();
    }

    private Payment paymentEntityToPayment(PaymentEntity paymentEntity) {
        return Payment.builder()
                .paymentId(paymentEntity.getPaymentId())
                .userId(paymentEntity.getUserId())
                .friendIds(paymentEntity.getFriendIds())
                .description(paymentEntity.getDescription())
                .amount(paymentEntity.getAmount())
                .date(paymentEntity.getDate())
                .status(paymentEntity.getStatus())
                .build();
    }

    @Override
    public Optional<Payment> findById(String paymentId) {
        final Optional<PaymentEntity> foundPaymentEntity = paymentRepository.findById(paymentId);
        final Optional<Payment> payment = foundPaymentEntity.map(paymentEntity -> paymentEntityToPayment(paymentEntity));
        return payment;
    }

}
