package com.hostel.studentservice.dto;

import java.util.List;

import com.hostel.studentservice.entities.Payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
	 private int studentId;
	   
	    private String studentName;
	   
	    private String email;
	    private String about;
	    private Boolean status;
}
