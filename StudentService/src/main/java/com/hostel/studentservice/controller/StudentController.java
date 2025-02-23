package com.hostel.studentservice.controller;

import com.hostel.studentservice.dto.StudentDTO;
import com.hostel.studentservice.entities.Leave;
import com.hostel.studentservice.entities.Payment;
import com.hostel.studentservice.entities.Student;
import com.hostel.studentservice.service.StudentService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {

    //? Inject the dependency for StudentService
    StudentService studentService;
    
    public StudentController(StudentService studentService) {
    	this.studentService = studentService;
    }
    
    //? Add a new student
    @PostMapping("/saveStudent")
    public StudentDTO saveStudent(@Valid @RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    //? Needed for both student and warden service

    //? Get a student based on student id
    @GetMapping("/getStudentById/{id}")
    public StudentDTO getStudentById(@PathVariable("id") int studentId){
        return studentService.getStudentById(studentId);
    }

    //? Delete a student
    @DeleteMapping("/deleteStudent/{id}")
    public boolean deleteStudent(@PathVariable("id") int studentId){
        StudentDTO student = studentService.getStudentById(studentId);
        
        return studentService.deleteStudent(studentId);
    }



//? Needed for warden service

    //? Login
    @PostMapping("/login")
    public StudentDTO login(@RequestBody Student student){

        return studentService.login(student);
    }


    //? Get all the students
    @GetMapping("/getAllStudents")
    public List<StudentDTO> getAllStudents(){
        return studentService.getAllStudents();
    }
    //? Update a student (Used by warden)
    @PutMapping("/updateStudent")
    public StudentDTO updateStudent(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

//    ? Dealing with leaveService

    //? Add a leave to Leave Service
    @PostMapping("/leave")
    public Leave applyLeave(@RequestBody Leave leave){
        return studentService.applyLeave(leave);
    }

    //? Get a leave by leave Id from Leave Service
    @GetMapping("/leave/{leaveId}")
    public Leave getLeave(@PathVariable int leaveId){
        return studentService.getLeave(leaveId);
    }

    //? Delete a leave from Leave service
    @DeleteMapping("/leave/{leaveId}")
    public String deleteLeave(@PathVariable int leaveId){
        return studentService.deleteLeave(leaveId);
    }

    //? Update a leave from Leave Service
    @PutMapping("/leave")
    public Leave updateLeave(@RequestBody Leave leave){
        return studentService.updateLeave(leave);
    }

    @GetMapping("/leave/getLeavesByStudentId/{studentId}")
    public List<Leave> getLeavesByStudentId(@PathVariable int studentId){
    	return studentService.getLeavesByStudentId(studentId);
    }

//? Student with PaymentService

    //? Get all the payments from Payment Service
    @GetMapping("/allPayments/{studentId}")
    public List<Payment> allPaymentsByStudentId(@PathVariable int studentId){
        return studentService.getPayments(studentId);
    }
    @GetMapping("/allPendingPayments/{studentId}")
    public List<Payment> allPendingPaymentsByStudentId(@PathVariable int studentId){
        return studentService.getPendingPayments(studentId);
    }

    //? Get a single payment from Payment Service
    @GetMapping("/payment/{payId}")
    public Payment getPayment(@PathVariable int payId){
        return  studentService.getPayment(payId);
    }

    //? Update a payment to Payment Service
    @PutMapping("/payment")
    public Payment updatePayment(@RequestBody Payment payment){
        return studentService.updatePayment(payment);
    }
}



