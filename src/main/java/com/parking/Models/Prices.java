package com.parking.Models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prices {

	private int twoWheelerPrice;
	
	private int threeWheelerPrice;
	
	private int fourWheelerPrice;
	
	
}
