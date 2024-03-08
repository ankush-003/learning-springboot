package com.ankush003.jshare.services;

import com.ankush003.jshare.domain.Payment;
import com.ankush003.jshare.domain.PaymentEntity;
import com.ankush003.jshare.repositories.PaymentRepository;
import com.ankush003.jshare.services.impl.PaymentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.ankush003.jshare.TestData.testPayment;
import static com.ankush003.jshare.TestData.testPaymentEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl underTest;

    @Test
    public void testThatPaymentIsSaved() {
        final Payment payment = testPayment();

        final PaymentEntity paymentEntity = testPaymentEntity();

        when(paymentRepository.save(eq(paymentEntity))).thenReturn(paymentEntity);

        final Payment result = underTest.create(payment);

        assertEquals(payment, result);

    }

    @Test
    public void testThatFindByIdReturnsEmptyWhenNoPaymentIsFound() {
        final String paymentId = "1";

        when(paymentRepository.findById(eq(paymentId))).thenReturn(java.util.Optional.empty());

        final java.util.Optional<Payment> result = underTest.findById(paymentId);

        assertEquals(java.util.Optional.empty(), result);
    }


}
