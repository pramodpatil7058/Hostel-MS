package com.hostel.wardenservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WardenServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WardenServiceApplication.class, args);
	}

}
