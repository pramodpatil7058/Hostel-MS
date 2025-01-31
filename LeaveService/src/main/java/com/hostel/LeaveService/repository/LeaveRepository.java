package com.hostel.LeaveService.repository;

import com.hostel.LeaveService.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {
    List<Leave> findByStudentId(int studentId);
}
