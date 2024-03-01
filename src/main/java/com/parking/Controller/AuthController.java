package com.parking.Controller;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.Config.JwtProvider;
import com.parking.Exceptions.UserException;
import com.parking.Models.User;
import com.parking.Repository.UserRepository;
import com.parking.Request.LoginRequest;
import com.parking.Response.AuthResponse;
import com.parking.Service.CustomUserServiceImplementation;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private CustomUserServiceImplementation customUserServiceImplementation; 
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> signupHandler(@RequestBody User user) throws UserException {
		
		String email = user.getEmail();
		String password = user.getPassword();
		String fullName = user.getFullName();
		
		User isEmailExist = userRepository.findByEmail(email);
		
		if(isEmailExist != null) {
			throw new UserException("user already exist with email : " + email);
		}
		
		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setFullName(fullName);
		createdUser.setPassword(passwordEncoder.encode(password));
		
		User savedUser = userRepository.save(createdUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(email, savedUser.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);
		
		AuthResponse response = new AuthResponse();
		response.setToken(token);
		response.setMessage("Sign up successfull !");
		
		return new ResponseEntity<AuthResponse>(response, HttpStatus.CREATED);
		
	}
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signinHandler(@RequestBody LoginRequest request) {
		
		String email = request.getEmail();
		String password = request.getPassword();
		
		Authentication authentication = authenticate(email, password);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);
		
		AuthResponse response = new AuthResponse();
		
		response.setMessage("Sign in successfull !");
		response.setToken(token);
		
		return new ResponseEntity<AuthResponse>(response, HttpStatus.ACCEPTED);
		
	}
	
	public Authentication authenticate(String email, String password) {
		
		UserDetails userDetails = customUserServiceImplementation.loadUserByUsername(email);
		
		if(userDetails == null) {
			throw new BadCredentialsException("user not found with email - " + email);
		}
		
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid Password ..!");
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null ,userDetails.getAuthorities());
	}

}
