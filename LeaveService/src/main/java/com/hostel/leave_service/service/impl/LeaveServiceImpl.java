package com.hostel.leave_service.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hostel.leave_service.entity.Leave;
import com.hostel.leave_service.repository.LeaveRepository;
import com.hostel.leave_service.service.LeaveService;

import java.util.List;

/**
 * @implNote LeaveServiceImpl implements LeaveService which acts as a service layer for Leave service
 * @version 1.0.0
 *
 */

@Service
public class LeaveServiceImpl implements LeaveService {


    private LeaveRepository leaveRepository;
    

    public LeaveServiceImpl(LeaveRepository leaveRepository) {
    	this.leaveRepository = leaveRepository;
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    //? Logic for adding a service
    /**
     * Adds a new leave record.
     *
     * @param leave the Leave entity containing leave details (leaveId is auto-generated, includes studentId, reason, fromDate, toDate, and status).
     * @return the added Leave entity.
     */
    @Override
    public Leave addLeave(Leave leave) {
    	logger.info("Add Leave service executed");
        leave.setStatus(false);
        return leaveRepository.save(leave);
    }

    //? Logic for retrieving leave based on leave id
    /**
     * Returns a already saved Leave object based on leaveId.
     *
     * @param int leaveId
     * @return the added Leave entity.
     */
    @Override
    public Leave getLeave(int leaveId) {
    	logger.info("Get leave by leave id {}",leaveId);
        return leaveRepository.findById(leaveId).orElse(null);
    }

    //? Login to update leave
    /**
     * Updates a already saved Leave object based on updated Leave object.
     *
     * @param Leave Object
     * @return the Updated Leave entity.
     */
    @Override
    public Leave updateLeave(Leave leave) {
    	logger.info("Updated leave with leave id {}",leave.getLeaveId());
        Leave oldLeave = leaveRepository.findById(leave.getLeaveId()).orElse(null);
        if(oldLeave == null){
            return null;
        }
         oldLeave.setStatus(leave.getStatus());
        return leaveRepository.save(oldLeave);
    }

    //? Logic to delete a leave
    /**
     * Deletes a already saved Leave object based on leaveId.
     *
     * @param int leaveId
     * @return status as true or false.
     */
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
    /**
     * Returns a already saved Leave object based on leaveId.
     *
     * @return List of Leave object.
     */
    @Override
    public List<Leave> getAllLeaves() {
    	logger.info("Called get all leaves");
        return leaveRepository.findAll();
    }


    //? Logic for getting all the leaves for a student
    /**
     * Returns a already saved Leave object based on leaveId.
     *
     * @param int studentId.
     * @return List of Leaves based on student Id.
     */
    @Override
    public List<Leave> getAllLeavesByStudentId(int studentId) {
    	logger.info("Get all leaves by student id {}", studentId);
        return leaveRepository.findByStudentId(studentId);
    }
}
