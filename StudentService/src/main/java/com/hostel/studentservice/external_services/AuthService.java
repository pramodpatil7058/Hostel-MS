package com.hostel.studentservice.external_services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import feign.Headers;

@FeignClient("SECURITYSERVICE")
public interface AuthService {
	@GetMapping("/auth/getUsername")
	String getUsername(@RequestBody String token);
}
