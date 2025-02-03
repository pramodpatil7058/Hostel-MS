package com.hostel.LeaveService.controller;

import com.hostel.LeaveService.entity.Leave;
import com.hostel.LeaveService.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping
    public Leave addLeave(@RequestBody Leave leave){
        return leaveService.addLeave(leave);
    }

    @GetMapping("/{id}")
    public Leave getLeave(@PathVariable("id") int leaveId){
        return leaveService.getLeave(leaveId);
    }

    @GetMapping
    public List<Leave> getAllLeaves(){
        return leaveService.getAllLeaves();
    }

    @GetMapping("/all/{id}")
    public List<Leave> getAllLeavesByStudentId(@PathVariable("id") int studentId){
        return leaveService.getAllLeavesByStudentId(studentId);
    }

    @PutMapping
    public Leave updateLeave(@RequestBody Leave leave){
        return leaveService.updateLeave(leave);
    }

    @DeleteMapping("/{leaveId}")
    public String deleteLeave(@PathVariable int leaveId){
        return leaveService.deleteLeave(leaveId);
    }


//    TODO Implement getAllLeavesByStudentId(for student service), GetAllLeaves( For warden Service)

}
