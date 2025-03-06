package com.hostel.paymentservice.entity;


import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name="payments")
    public class Payment {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int payId;
        private long amount;
        private int studentId;
        private String payDate;
        private String reason;
        @Column(unique = true)
        private String transactionId;
        private Boolean payStatus;
    }
