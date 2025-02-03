package com.hostel.studentservice.controller;

import com.hostel.studentservice.entities.Leave;
import com.hostel.studentservice.entities.Payment;
import com.hostel.studentservice.entities.Student;
import com.hostel.studentservice.exception.CustomException;
import com.hostel.studentservice.exception.NoResourceFoundException;
import com.hostel.studentservice.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;


    @PostMapping("/saveStudent")
    public Student saveStudent(@Valid @RequestBody Student student) {
        Student savedStudent =studentService.saveStudent(student);
        if(savedStudent == null){
            throw new CustomException("Something went wrong");
        }
        return savedStudent;
    }



//? Needed for both student and warden service


    @GetMapping("/getStudentById/{id}")
    public Student getStudentById(@PathVariable("id") int studentId){
        Student student = studentService.getStudentById(studentId);
        if(student == null){
            throw new NoResourceFoundException("Student ID does not exists.");
        }
        return student;
    }

    @DeleteMapping("/deleteStudent/{id}")
    public boolean deleteStudent(@PathVariable("id") int studentId){

        if(!studentService.deleteStudent(studentId)){
            throw new NoResourceFoundException("Student ID does not exists.");
        }
        return true;
    }

    @PutMapping("/updateStudent")
    public Student updateStudent(@RequestBody Student student){
        Student student1 =studentService.updateStudent(student);
        if(student1 == null){
            throw new NoResourceFoundException("Student does not exists.");
        }
        return student1;

    }



//? Needed for warden service



    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }


//    ? Dealing with leaveService


    @PostMapping("/leave")
    public Leave applyLeave(@RequestBody Leave leave){
        Leave appliedLeave = studentService.applyLeave(leave);
        if(appliedLeave == null){
            throw new CustomException("Something went wrong");
        }
        return appliedLeave;
    }

    @GetMapping("/leave/{leaveId}")
    public Leave getLeave(@PathVariable int leaveId){
        return studentService.getLeave(leaveId);
    }

    @DeleteMapping("/leave/{leaveId}")
    public String deleteLeave(@PathVariable int leaveId){
        return studentService.deleteLeave(leaveId);
    }

    @PutMapping("/leave")
    public Leave updateLeave(@RequestBody Leave leave){
        return studentService.updateLeave(leave);
    }


//? Student with PaymentService

    @GetMapping("/allPayments/{studentId}")
    public List<Payment> allPaymentsByStudentId(@PathVariable int studentId){
        return null;
    }

    @GetMapping("/payment/{payId}")
    public Payment getPayment(@PathVariable int payId){
        return studentService.getPayment(payId);
    }

    @PutMapping("/payment")
    public Payment updatePayment(Payment payment){
        return studentService.updatePayment(payment);
    }
}



