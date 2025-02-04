package com.hostel.leave_service.controller;

import org.springframework.web.bind.annotation.*;

import com.hostel.leave_service.entity.Leave;
import com.hostel.leave_service.service.LeaveService;

import java.util.List;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    //? Inject the dependency for Leave Service
    private LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
    	this.leaveService = leaveService;
    }
    
    //? Add leave
    @PostMapping
    public Leave addLeave(@RequestBody Leave leave){
        return leaveService.addLeave(leave);
    }

    //? Get leave based on Leave Id
    @GetMapping("/{id}")
    public Leave getLeave(@PathVariable("id") int leaveId){
        return leaveService.getLeave(leaveId);
    }


    //? Get all the leaves present
    @GetMapping
    public List<Leave> getAllLeaves(){
        return leaveService.getAllLeaves();
    }

    //? Get all the leaves for a particular student by using Student Id
    @GetMapping("/getLeavesByStudentId/{studentId}")
    public List<Leave> getAllLeavesByStudentId(@PathVariable int studentId){
        return leaveService.getAllLeavesByStudentId(studentId);
    }

    //? Update the leave (Warden can approve or reject the leave)
    @PutMapping
    public Leave updateLeave(@RequestBody Leave leave){
        return leaveService.updateLeave(leave);
    }

    //? Delete leave
    @DeleteMapping("/{leaveId}")
    public String deleteLeave(@PathVariable int leaveId){
        return leaveService.deleteLeave(leaveId);
    }


}
