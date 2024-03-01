package com.parking.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prices {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	private int twoWheelerPrice;
	
	private int threeWheelerPrice;
	
	private int fourWheelerPrice;
	
	
}
