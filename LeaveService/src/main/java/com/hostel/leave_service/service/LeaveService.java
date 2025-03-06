package com.hostel.leave_service.service;

import org.springframework.stereotype.Service;

import com.hostel.leave_service.entity.Leave;

import java.util.List;

@Service
public interface LeaveService {
	/**
     * Adds a new leave record.
     *
     * @param leave the Leave entity containing leave details (leaveId is auto-generated, includes studentId, reason, fromDate, toDate, and status).
     * @return the added Leave entity.
     */
    Leave addLeave(Leave leave);
    
    /**
     * Returns a already saved Leave object based on leaveId.
     *
     * @param int leaveId
     * @return the added Leave entity.
     */
    Leave getLeave(int leaveId);
    
    /**
     * Updates a already saved Leave object based on updated Leave object.
     *
     * @param Leave Object
     * @return the Updated Leave entity.
     */
    Leave updateLeave(Leave leave);
    
    /**
     * Deletes a already saved Leave object based on leaveId.
     *
     * @param int leaveId
     * @return status as true or false.
     */
    String deleteLeave(int leaveId);
    
    /**
     * Returns a already saved Leave object based on leaveId.
     *
     * @return List of Leave object.
     */
    List<Leave> getAllLeaves();
    
    /**
     * Returns a already saved Leave object based on leaveId.
     *
     * @param int studentId.
     * @return List of Leaves based on student Id.
     */
    List<Leave> getAllLeavesByStudentId(int studentId);

	void deleteAllLeavesByStudentId(int userId);

}
