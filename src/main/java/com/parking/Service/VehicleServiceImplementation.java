package com.parking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.Exceptions.UserException;
import com.parking.Models.User;
import com.parking.Models.Vehicle;
import com.parking.Repository.UserRepository;
import com.parking.Repository.VehicleRepository;
import com.parking.Request.CreateVehicleRequest;

@Service
public class VehicleServiceImplementation implements VehicleService {
	
	@Autowired
	private VehicleRepository vehicleRepository;
	


	@Override
	public Vehicle createVehicle(String vehicleNumber)  {
		
		Vehicle createdVehicle = new Vehicle();
		createdVehicle.setVehicleNumber(vehicleNumber);
		vehicleRepository.save(createdVehicle);
		
		return createdVehicle;
	}

}
