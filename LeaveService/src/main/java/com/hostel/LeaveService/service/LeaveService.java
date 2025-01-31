package com.hostel.LeaveService.service;

import com.hostel.LeaveService.entity.Leave;
import org.springframework.stereotype.Service;

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
