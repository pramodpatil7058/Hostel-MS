package com.hostel.studentservice.externalServices;

import com.hostel.studentservice.entities.Leave;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("LEAVESERVICE")
public interface LeaveServices {

    @PostMapping("/leave")
    Leave applyLeave(@RequestBody Leave leave);

    @GetMapping("/leave/{leaveId}")
    Leave getLeave(@PathVariable int leaveId);

    @PutMapping("/leave")
    Leave updateLeave(@RequestBody Leave leave);

    @DeleteMapping("/leave/{leaveId}")
    String deleteLeave(@PathVariable int leaveId);
}
