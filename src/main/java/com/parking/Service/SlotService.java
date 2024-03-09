package com.parking.Service;

import java.util.List;

import com.parking.Exceptions.SlotException;
import com.parking.Models.Slot;
import com.parking.Request.BookSlotRequest;
import com.parking.Request.CreateSlotRequest;
import com.parking.Response.Response;

public interface SlotService {
	
	//	Create a slot
	public Slot createSlot(CreateSlotRequest req);
	
    //  Remove slot
	public Response deleteSlot(Long slotId) throws SlotException;
	
	public List<Slot> findAllSlots();
	
	public Response bookSlot(BookSlotRequest req);
	
//	public Response createMultipleSlots(List<CreateSlotRequest> requests);
	
}
