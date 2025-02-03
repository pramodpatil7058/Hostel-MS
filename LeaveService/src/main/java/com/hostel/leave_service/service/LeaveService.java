package com.hostel.leave_service.service;

import org.springframework.stereotype.Service;

import com.hostel.leave_service.entity.Leave;

import java.util.List;

@Service
public interface LeaveService {
    Leave addLeave(Leave leave);
    Leave getLeave(int leaveId);
    Leave updateLeave(Leave leave);
    String deleteLeave(int leaveId);
    List<Leave> getAllLeaves();
    List<Leave> getAllLeavesByStudentId(int studentId);

}
