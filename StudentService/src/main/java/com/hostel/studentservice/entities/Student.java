package com.hostel.studentservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="students")
@Data
public class Student {

    @Id
    @GeneratedValue( generator = "CUST_GEN")
    @Column(name = "stid")
    private int studentId;
    @Column(name = "stname")
    private String studentName;
    @Column(name = "stemail")
    @NotNull(message = "Email cannot be null")
    private String email;
    @Column(name = "stabout")
    private String about;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "password")
    private String password;
    @Transient
    public List<Payment> payments = new ArrayList<>();
}
