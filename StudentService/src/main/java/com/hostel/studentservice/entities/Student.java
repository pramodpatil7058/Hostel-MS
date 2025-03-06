package com.hostel.studentservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="students")
@Data

public class Student {

    @Id
    @Column(name = "stid")
    private int studentId;
    private String studentName;
    private String email;
    @Column(name = "stabout")
    private String about;
    @Column(name = "status")
    private Boolean status;
    @Column(name="address")
    private String address;
    @Column(name="branch")
    private String branch;
    @Column(name="year_of_study")
    private String yos;
	
}
