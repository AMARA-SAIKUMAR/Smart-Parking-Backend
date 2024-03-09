package com.parking.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.parking.Exceptions.SlotException;
import com.parking.Models.Slot;
import com.parking.Repository.SlotRepository;
import com.parking.Request.CreateSlotRequest;
import com.parking.Response.Response;

@Service
public class SlotServiceImplementation implements SlotService {
	
	@Autowired
	private SlotRepository slotRepository;

	@Override
	public Slot createSlot(CreateSlotRequest req) {
		
		Slot createdSlot = new Slot();
		createdSlot.setOccupancyStatus("free");
		createdSlot.setWheelerType(req.getWheelerType());
		
		return slotRepository.save(createdSlot);
	}

	@Override
	public Response deleteSlot(Long slotId) throws SlotException {
		
		slotRepository.deleteById(slotId);
		
		Response response = new Response();
		response.setStatus(true);
		response.setMessage("Slot deleted successfully with id - " + slotId);
		
		return response;
	}

	@Override
	public List<Slot> findAllSlots() {
		
		List<Slot> allSlots = slotRepository.findAll();
		
		return allSlots;
	}

//	@Override
//	public Response createMultipleSlots(List<CreateSlotRequest> requests) {
//		
//		
//		
//		return null;
//	}
	
	

}
