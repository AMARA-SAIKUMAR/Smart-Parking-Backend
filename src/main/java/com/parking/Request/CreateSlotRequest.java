package com.parking.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSlotRequest {
	
	private String wheelerType;
	
	private int noOfSlots;
	

}
