package com.parking.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//@Data
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Prices {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	int twoWheelerPrice;
	
	int threeWheelerPrice;
	
	int fourWheelerPrice;
	
	
}
