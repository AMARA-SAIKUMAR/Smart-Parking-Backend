package com.parking.Request;

import java.time.LocalTime;

import lombok.Data;

@Data
public class BookSlotRequest {
	
	Long slotId;
	
	String bookedTime;
	
	float parkHours;
	
	String vehicleNumber;
	
	int amount;
	
}
