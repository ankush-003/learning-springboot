package com.ankush003.jshare.services.impl;

import com.ankush003.jshare.domain.Payment;
import com.ankush003.jshare.dao.impl.PaymentDaoImpl;
import com.ankush003.jshare.repositories.PaymentRepository;
import com.ankush003.jshare.services.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
    @Override
    public Payment save(Payment payment) {
        final PaymentDaoImpl paymentDaoImpl = paymentToPaymentEntity(payment);
        final PaymentDaoImpl savedPaymentDaoImpl =  paymentRepository.save(paymentDaoImpl);
        return paymentEntityToPayment(savedPaymentDaoImpl);
    }

    private PaymentDaoImpl paymentToPaymentEntity(Payment payment) {
        return PaymentDaoImpl.builder()
                .paymentId(payment.getPaymentId())
                .userId(payment.getUserId())
                .friendIds(payment.getFriendIds())
                .description(payment.getDescription())
                .amount(payment.getAmount())
                .date(payment.getDate())
                .status(payment.getStatus())
                .build();
    }

    private Payment paymentEntityToPayment(PaymentDaoImpl paymentDaoImpl) {
        return Payment.builder()
                .paymentId(paymentDaoImpl.getPaymentId())
                .userId(paymentDaoImpl.getUserId())
                .friendIds(paymentDaoImpl.getFriendIds())
                .description(paymentDaoImpl.getDescription())
                .amount(paymentDaoImpl.getAmount())
                .date(paymentDaoImpl.getDate())
                .status(paymentDaoImpl.getStatus())
                .build();
    }

    @Override
    public Optional<Payment> findById(String paymentId) {
        final Optional<PaymentDaoImpl> foundPaymentEntity = paymentRepository.findById(paymentId);
        return foundPaymentEntity.map(paymentDaoImpl -> paymentEntityToPayment(paymentDaoImpl));
    }

    @Override
    public List<Payment> listPayments() {
        final List<PaymentDaoImpl> foundPayments = paymentRepository.findAll();
        return foundPayments.stream().map(paymentDaoImpl -> paymentEntityToPayment(paymentDaoImpl)).collect(Collectors.toList());
    }

    @Override
    public boolean isBookExists(Payment payment) {
        return paymentRepository.existsById(payment.getPaymentId());
    }

    @Override
    public void deleteById(String paymentId) {
        try {
            paymentRepository.deleteById(paymentId);
        } catch (Exception e) {
            log.debug("Error while deleting payment with id: {}", paymentId, e);
        }
    }
}
