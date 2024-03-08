package com.ankush003.jshare.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// payment for service layer
public class Payment {
    private String paymentId;
    private String userId;
    private String[] friendIds;
    private String description;
    private String amount;
    private String date;
    private String status;
}
