package com.hostel.leave_service.service.impl;

import org.springframework.stereotype.Service;

import com.hostel.leave_service.entity.Leave;
import com.hostel.leave_service.repository.LeaveRepository;
import com.hostel.leave_service.service.LeaveService;

import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {


    //? Inject the dependancy for LeaveRepository
    private LeaveRepository leaveRepository;
    

    public LeaveServiceImpl(LeaveRepository leaveRepository) {
    	this.leaveRepository = leaveRepository;
    }

    //? Logic for adding a service
    @Override
    public Leave addLeave(Leave leave) {
        leave.setStatus(false);
        return leaveRepository.save(leave);
    }

    //? Logic for retrieving leave based on leave id
    @Override
    public Leave getLeave(int leaveId) {
        return leaveRepository.findById(leaveId).orElse(null);
    }

    //? Login to update leave
    @Override
    public Leave updateLeave(Leave leave) {
        Leave oldLeave = leaveRepository.findById(leave.getLeaveId()).orElse(null);
        if(oldLeave == null){
            return null;
        }
         oldLeave = leave;
        return leaveRepository.save(oldLeave);
    }

    //? Logic to delete a leave
    @Override
    public String deleteLeave(int leaveId) {
        Leave leave = leaveRepository.findById(leaveId).orElse(null);
        if(leave == null){
            return null;
        }
        leaveRepository.deleteById(leaveId);
        return "Leave deleted successfully";
    }

    //? Logic for getting all Leaves
    @Override
    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }


    //? Logic for getting all the leaves for a student
    @Override
    public List<Leave> getAllLeavesByStudentId(int studentId) {
        return leaveRepository.findByStudentId(studentId);
    }
}
