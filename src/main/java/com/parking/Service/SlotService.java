package com.parking.Service;

import java.util.List;

import com.parking.Exceptions.SlotException;
import com.parking.Models.Order;
import com.parking.Models.Slot;
import com.parking.Models.User;
import com.parking.Request.BookSlotRequest;
import com.parking.Request.CreateSlotRequest;
import com.parking.Response.Response;

public interface SlotService {
	
	//	Create a slot
	public Slot createSlot(String wheelerType);
	
    //  Remove slot
	public Response deleteSlot(Long slotId) throws SlotException;
	
	public List<Slot> findAllSlots();
	
	public Order bookSlot(BookSlotRequest req);
	
	
	// Write a method to free up the slot vacancy after parking hours
	public Slot freeUpSlot(Long slotId);
	
	public List<Slot> findSlotsByType(String slotType);
	
	// Add the payment Details in the 
	
}
