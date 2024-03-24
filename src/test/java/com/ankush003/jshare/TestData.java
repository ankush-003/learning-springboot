package com.ankush003.jshare;

import com.ankush003.jshare.domain.Payment;
import com.ankush003.jshare.dao.impl.PaymentDaoImpl;

public class TestData {

    private TestData() {}

    public static Payment testPayment() {
        return Payment.builder()
                .paymentId("1")
                .userId("2")
                .friendIds(new String[]{"3"})
                .description("test")
                .amount("100")
                .date("2021-01-01")
                .status("PENDING")
                .build();
    }

    public static PaymentDaoImpl testPaymentEntity() {
        return PaymentDaoImpl.builder()
                .paymentId("1")
                .userId("2")
                .friendIds(new String[]{"3"})
                .description("test")
                .amount("100")
                .date("2021-01-01")
                .status("PENDING")
                .build();
//        sample json
//        {
//            "paymentId": "1",
//                "userId": "2",
//                "friendIds": ["3"],
//                "description": "test",
//                "amount": "100",
//                "date": "2021-01-01",
//                "status": "PENDING"
//        }
    }
}
