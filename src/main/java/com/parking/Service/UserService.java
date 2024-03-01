package com.parking.Service;

import com.parking.Exceptions.UserException;
import com.parking.Models.User;

public interface UserService {
	
	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;

}
