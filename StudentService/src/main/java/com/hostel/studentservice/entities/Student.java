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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stid")
    private int studentId;
    @NotNull(message = "Student name cannot be null")
    @Length(min = 3, max = 20, message = "Name should be greater than 3 and smaller than 20 chars")
    @Column(name = "stname")
    private String studentName;
    @Column(name = "stemail")
    @NotNull(message = "Email cannot be null")
    @Pattern(regexp = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")
    private String email;
    @Column(name = "stabout")
    private String about;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "password")
    private String password;
}
