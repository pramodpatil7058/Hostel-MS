package com.hostel.wardenservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Leave {
	private int leaveId;
	private String reason;
	private String fromDate;
	private String toDate;
	private Boolean status;
}