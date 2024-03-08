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

import java.util.List;
import java.util.Optional;

import static com.ankush003.jshare.TestData.testPayment;
import static com.ankush003.jshare.TestData.testPaymentEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

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

        final Payment result = underTest.save(payment);

        assertEquals(payment, result);

    }

    @Test
    public void testThatFindByIdReturnsEmptyWhenNoPaymentIsFound() {
        final String paymentId = "1234567";

        when(paymentRepository.findById(eq(paymentId))).thenReturn(Optional.empty());

        final Optional<Payment> result = underTest.findById(paymentId);

        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testThatFindByIdReturnsPaymentWhenPaymentIsFound() {
        final Payment payment = testPayment();
        final PaymentEntity paymentEntity = testPaymentEntity();

        when(paymentRepository.findById(eq(payment.getPaymentId()))).thenReturn(Optional.of(paymentEntity));

        final Optional<Payment> result = underTest.findById(payment.getPaymentId());

        assertEquals(Optional.of(testPayment()), result);
    }

    @Test
    public void testListPaymentsWhenNoPaymentsAreFound() {
        when(paymentRepository.findAll()).thenReturn(List.of());

        final List<Payment> result = underTest.listPayments();

        assertEquals(List.of(), result);
    }

    @Test
    public void testListPaymentsWhenPaymentsAreFound() {
        final Payment payment = testPayment();
        final PaymentEntity paymentEntity = testPaymentEntity();

        when(paymentRepository.findAll()).thenReturn(List.of(paymentEntity));

        final List<Payment> result = underTest.listPayments();

        assertEquals(List.of(payment), result);
    }

    @Test
    public void testThatPaymentExists() {
        final Payment payment = testPayment();
        final PaymentEntity paymentEntity = testPaymentEntity();

        when(paymentRepository.existsById(eq(payment.getPaymentId()))).thenReturn(true);

        final boolean result = underTest.isBookExists(payment);

        assertEquals(true, result);
    }

    @Test
    public void testThatPaymentIsDeleted() {
        final Payment payment = testPayment();
        final PaymentEntity paymentEntity = testPaymentEntity();

        underTest.deleteById(payment.getPaymentId());
        verify(paymentRepository, times(1)).deleteById(payment.getPaymentId());
    }
}
