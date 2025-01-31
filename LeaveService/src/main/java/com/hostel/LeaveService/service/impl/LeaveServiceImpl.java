package com.hostel.LeaveService.service.impl;

import com.hostel.LeaveService.repository.LeaveRepository;
import com.hostel.LeaveService.entity.Leave;
import com.hostel.LeaveService.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Override
    public Leave addLeave(Leave leave) {
        leave.setStatus(false);
        return leaveRepository.save(leave);
    }

    @Override
    public Leave getLeave(int leaveId) {
        return leaveRepository.findById(leaveId).orElse(null);
    }

    @Override
    public Leave updateLeave(Leave leave) {
        Leave oldLeave = leaveRepository.findById(leave.getLeaveId()).orElse(null);
        if(oldLeave == null){
            return null;
        }
         oldLeave = leave;
        return leaveRepository.save(oldLeave);
    }

    @Override
    public String deleteLeave(int leaveId) {
        Leave leave = leaveRepository.findById(leaveId).orElse(null);
        if(leave == null){
            return null;
        }
        leaveRepository.deleteById(leaveId);
        return "Leave deleted successfully";
    }

    @Override
    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    @Override
    public List<Leave> getAllLeavesByStudentId(int studentId) {
        return leaveRepository.findByStudentId(studentId);
    }
}
