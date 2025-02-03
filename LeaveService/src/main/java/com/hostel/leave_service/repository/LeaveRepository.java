package com.hostel.leave_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hostel.leave_service.entity.Leave;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {
    List<Leave> findByStudentId(int studentId);
}
