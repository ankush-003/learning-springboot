package com.ankush003.jshare.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// entity for persisting payment details (persistence layer)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "payments")
public class PaymentEntity {
    @Id
    private String paymentId;
    private String userId;
    private String[] friendIds;
    private String description;
    private String amount;
    private String date;
    private String status;
}
