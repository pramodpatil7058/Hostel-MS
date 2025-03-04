package com.hostel.studentservice.mapper;

import com.hostel.studentservice.dto.StudentDTO;
import com.hostel.studentservice.entities.Student;

public class StudentMapper {
	private StudentMapper() {
		
	}
	public static StudentDTO mapToStudentDTO(Student student) {
		return new StudentDTO(
				student.getStudentId(),
				student.getStudentName(),
				student.getEmail(),
				student.getAbout(),
				student.getStatus(),
				student.getRole()
		);
	}
}
