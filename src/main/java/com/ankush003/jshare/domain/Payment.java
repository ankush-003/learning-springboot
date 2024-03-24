package com.ankush003.jshare.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// payment for service layer
public class Payment {
    private Long id;
    private String userId;
    private String friendId;
    private String description;
    private String amount;
    private Timestamp paymentDate;
    private String status;
}
