package com.hostel.wardenservice.controller;

import com.hostel.wardenservice.entity.Leave;
import com.hostel.wardenservice.entity.Payment;
import com.hostel.wardenservice.entity.Student;
import com.hostel.wardenservice.exception.ResourceNotFoundException;
import com.hostel.wardenservice.service.WardenServices;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warden")
public class WardenController {

    private WardenServices wardenServices;

    public WardenController(WardenServices wardenServices) {
    	this.wardenServices = wardenServices;
    }
    
//    ? Needed from StudentService


    @GetMapping("/changeApplicationStatus")
    public Student changeApplicationStatus(@RequestParam("studentId") int studentId, @RequestParam("status") boolean status){
        Student student = wardenServices.changeApplicationStatus(studentId, status);
        return student;
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students = wardenServices.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @DeleteMapping("/removeStudent/{studentId}")
    public String removeStudent(@PathVariable int studentId){
        return wardenServices.removeStudent(studentId);
    }



//    ?Needed from PaymentService

    @PostMapping("/addPayment")
    public Payment addPayment(@RequestBody Payment payment){
        return wardenServices.addPayment(payment);
    }

    @GetMapping("/getAllPendingPayments/{studentId}")
    public ResponseEntity<List<Payment>> getAllPendingPayments(@PathVariable int studentId){
        List<Payment> pendingPayments = wardenServices.getAllPendingPayments(studentId);
        return ResponseEntity.ok(pendingPayments);
    }

    @GetMapping("/getAllPayments")
    public ResponseEntity<List<Payment>> getAllPayments(){
        List<Payment> payments = wardenServices.getAllPayments();
        return ResponseEntity.ok(payments);
    }
    //? Dealing with LeaveService

    @GetMapping("/leave/{leaveId}")
    public Leave getLeave(@PathVariable int leaveId){
        return wardenServices.getLeave(leaveId);
    }

    @DeleteMapping("/leave/{leaveId}")
    public String deleteLeave(@PathVariable int leaveId){
        return wardenServices.deleteLeave(leaveId);
    }

    @PutMapping("/leave")
    public Leave updateLeave(@RequestBody Leave leave){
        return wardenServices.updateLeave(leave);
    }
}
