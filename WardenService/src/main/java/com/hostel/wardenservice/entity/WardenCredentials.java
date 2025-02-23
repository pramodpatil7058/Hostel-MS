package com.hostel.wardenservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WardenCredentials {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "warden_id")
	private int wardenId;
	@Column(name = "warden_name")
	private String wardenName;
	@Column(name = "warden_password")
	private String wardenPassword;
}
