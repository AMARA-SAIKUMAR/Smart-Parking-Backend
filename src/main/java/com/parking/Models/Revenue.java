package com.parking.Models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Revenue {
	
	private Long twoWheelerRevenue;
	
	private Long threeWheelerRevenue;
	
	private Long fourWheelerRevenue;
	
	private Long totalRevenue;

}
