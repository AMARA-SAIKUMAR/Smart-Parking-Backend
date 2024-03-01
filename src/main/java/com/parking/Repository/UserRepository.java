package com.parking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parking.Models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);

}
