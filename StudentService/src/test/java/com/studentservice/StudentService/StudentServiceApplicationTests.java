package com.studentservice.StudentService;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
import com.hostel.studentservice.StudentServiceApplication;
//import com.hostel.studentservice.dto.StudentDTO;
//import com.hostel.studentservice.entities.Payment;
//import com.hostel.studentservice.entities.Student;
////import com.hostel.studentservice.exception.ResourceNotFoundException;
//import com.hostel.studentservice.external_services.LeaveServices;
//import com.hostel.studentservice.external_services.PaymentServices;
//import com.hostel.studentservice.repository.StudentRepository;
//import com.hostel.studentservice.service.impl.StudentServiceImpl;
//
@SpringBootTest(classes = StudentServiceApplication.class)
class StudentServiceApplicationTests {
//
//	@Mock
//	private StudentRepository studentRepository;
//	
//	@Mock
//	private PaymentServices paymentServices;
//	
//	@Mock
//	PasswordEncoder passwordEncoder;
//	
//	@Mock
//	private LeaveServices leaveServices;
//	
//	@InjectMocks
//	private StudentServiceImpl studentServiceImpl;
//	
//	List<Student> students = new ArrayList<>();
//	List<StudentDTO> studentsDTOs = new ArrayList<>();
//	List<Payment> payments = new ArrayList<>();
//	Student student = new Student(1,"Pramod","pramod@gmail.com","ABCD",false,null);
//	StudentDTO studentDTO = new StudentDTO(1,"Pramod","pramod@gmail.com","ABCD",false);
//	
//	@BeforeEach
//	void setup() {
//		students.add(new Student(1,"Pramod","pramod@gmail.com","ABCD",false,null));
//		students.add(new Student(2,"Sahil","pramod@gmail.com","ABCD",false,null));
//		students.add(new Student(3,"Amber","pramod@gmail.com","ABCD",false,null));
//		students.add(new Student(4,"Subhanshu","pramod@gmail.com","ABCD",false,null));
//		students.add(new Student(5,"Devansh","pramod@gmail.com","ABCD",false,null));
//		
//		
//		studentsDTOs.add(new StudentDTO(1,"Pramod","pramod@gmail.com","ABCD",false));
//		studentsDTOs.add(new StudentDTO(2,"Sahil","pramod@gmail.com","ABCD",false));
//		studentsDTOs.add(new StudentDTO(3,"Amber","pramod@gmail.com","ABCD",false));
//		studentsDTOs.add(new StudentDTO(4,"Subhanshu","pramod@gmail.com","ABCD",false));
//		studentsDTOs.add(new StudentDTO(5,"Devansh","pramod@gmail.com","ABCD",false));
//		
//		
//		payments.add(new Payment(1,100,1,"12-12-2024","Fine","C-1234",true));
//		payments.add(new Payment(2,1100,1,"12-12-2024","Fees","C-1235",false));
//		payments.add(new Payment(3,1000,1,"12-12-2024","Fine","C-1236",true));
//		payments.add(new Payment(4,11100,1,"12-12-2024","Fees","C-1237",false));
//		payments.add(new Payment(5,10000,1,"12-12-2024","Fine","C-1238",true));
//	}
//	
//	@Test
//	void testAddStudent() {
//		when(studentRepository.save(student)).thenReturn(student);
//		when(passwordEncoder.encode(student.getPassword())).thenReturn(student.getPassword());
//		assertEquals(studentDTO, studentServiceImpl.saveStudent(student));
//	}
//	
//	@Test
//	void testGetStudentById(){
//		int studentId = 1;
//		when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
//		when(paymentServices.getPayments(studentId)).thenReturn((ArrayList<Payment>) payments);
//		assertEquals(studentDTO, studentServiceImpl.getStudentById(studentId));
//	}
//
////	@Test
////	void getStudentByInvalidStudentId() {
////		int studentId = 0;
////		when(studentRepository.findById(studentId)).thenReturn(Optional.empty());
////		assertThrows(ResourceNotFoundException.class, ()->{
////			studentServiceImpl.getStudentById(studentId);
////		});
////	}
//	
//	@Test
//	void testGetAllStudents() {
//		when(studentRepository.findAll()).thenReturn(students);
//		assertEquals(studentsDTOs, studentServiceImpl.getAllStudents());
//	}
}
