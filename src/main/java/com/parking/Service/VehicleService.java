package com.parking.Service;

import com.parking.Exceptions.UserException;
import com.parking.Models.Vehicle;
import com.parking.Request.CreateVehicleRequest;

public interface VehicleService {
	
	// Add Vehicle 
	public Vehicle createVehicle(String vehicleNumber);

}
