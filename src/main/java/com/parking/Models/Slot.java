package com.parking.Models;

import java.time.LocalTime;

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
public class Slot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String occupancyStatus;
	
	private LocalTime parkedOn;
	
	private LocalTime departuredOn;
	
	private int parkedTime;
	
	private Long vehicleId;
	
	private String bookedMonth;
	
	private String wheelerType;
	
	private int Amount;
	
	
}
