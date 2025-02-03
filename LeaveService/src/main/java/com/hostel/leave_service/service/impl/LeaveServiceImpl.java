package com.hostel.leave_service.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    //? Logic for adding a service
    @Override
    public Leave addLeave(Leave leave) {
    	logger.info("Add Leave service executed");
        leave.setStatus(false);
        return leaveRepository.save(leave);
    }

    //? Logic for retrieving leave based on leave id
    @Override
    public Leave getLeave(int leaveId) {
    	logger.info("Get leave by leave id {}",leaveId);
        return leaveRepository.findById(leaveId).orElse(null);
    }

    //? Login to update leave
    @Override
    public Leave updateLeave(Leave leave) {
    	logger.info("Updated leave with leave id {}",leave.getLeaveId());
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
    	logger.info("Deleted Leave with leave id {}",leaveId);
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
    	logger.info("Called get all leaves");
        return leaveRepository.findAll();
    }


    //? Logic for getting all the leaves for a student
    @Override
    public List<Leave> getAllLeavesByStudentId(int studentId) {
    	logger.info("Get all leaves by student id {}", studentId);
        return leaveRepository.findByStudentId(studentId);
    }
}
