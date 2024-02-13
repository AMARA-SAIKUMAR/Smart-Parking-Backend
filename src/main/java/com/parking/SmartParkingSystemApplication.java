package com.parking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartParkingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartParkingSystemApplication.class, args);
		System.out.println("App running successfully at port : 8080");
	}

}
