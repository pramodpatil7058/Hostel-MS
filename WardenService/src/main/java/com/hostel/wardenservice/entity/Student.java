package com.hostel.wardenservice.entity;

import jakarta.persistence.Column;
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
    private String about;
    private Boolean status;
    private String address;
    private String branch;
    private String yos;
}
