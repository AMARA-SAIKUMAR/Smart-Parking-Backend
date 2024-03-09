package com.parking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.Exceptions.UserException;
import com.parking.Models.User;
import com.parking.Service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findUserById(@PathVariable Long id) throws UserException {
		
		User user = userService.findUserById(id);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping("/profile")
	public ResponseEntity<User> findUserProfileHandler(@RequestHeader("Authorization") String jwt ) throws UserException {
		
		User user = userService.findUserProfileByJwt(jwt);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

}
