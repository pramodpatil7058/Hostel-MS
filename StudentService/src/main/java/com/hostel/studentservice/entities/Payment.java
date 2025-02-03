package com.hostel.studentservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {
    private int payId;
    private long amount;
    private int studentId;
    private String payDate;
    private String reason;
    private String transactionId;
    private Boolean payStatus;
}
