package com.hostel.wardenservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student
{
    private int studentId;
    private String studentName;
    private String email;
    private String about;
    private Boolean status;
}
