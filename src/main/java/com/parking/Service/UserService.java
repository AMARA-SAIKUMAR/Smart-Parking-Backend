package com.parking.Service;

import org.springframework.stereotype.Service;

import com.parking.Exceptions.UserException;
import com.parking.Models.User;

@Service
public interface UserService {
	
	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;

}
