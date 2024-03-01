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
	private UserService userService;
	@Autowired
	private VehicleRepository vehicleRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public Vehicle addVehicle(CreateVehicleRequest request, Long userId) throws UserException {
		
		Vehicle createdVehicle = new Vehicle();
		createdVehicle.setVehicleNumber(request.getVehicleNumber());
		vehicleRepository.save(createdVehicle);
		
		// Add the vehicle to user object
		User user = userService.findUserById(userId);
		user.getUserVehicles().add(createdVehicle);
		userRepository.save(user);
		
		
		return createdVehicle;
	}

}
