package com.parking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.Exceptions.UserException;
import com.parking.Models.User;
import com.parking.Models.Vehicle;
import com.parking.Request.CreateVehicleRequest;
import com.parking.Service.UserService;
import com.parking.Service.VehicleService;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public ResponseEntity<Vehicle> addVehicleHandler(@RequestBody String vehicleNumber) {
		
		Vehicle vehicle = vehicleService.createVehicle(vehicleNumber);
		
		return new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);
		
	}

}
