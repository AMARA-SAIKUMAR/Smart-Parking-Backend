package com.parking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.Exceptions.SlotException;
import com.parking.Models.Slot;
import com.parking.Request.CreateSlotRequest;
import com.parking.Response.Response;
import com.parking.Service.SlotService;

@RestController
@RequestMapping("/api/slot")
public class SlotController {
	
	@Autowired
	private SlotService slotService;
	
	@PostMapping("/create") 
	public ResponseEntity<Slot> createSlot(@RequestBody CreateSlotRequest req ) {
		
		Slot createSlot = slotService.createSlot(req);
		
		return new ResponseEntity<Slot>(createSlot, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteSlot(@PathVariable Long id) throws SlotException {
		
		Response response = slotService.deleteSlot(id);
		
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Slot>> findAllSlots() {
		
		List<Slot> allSlots = slotService.findAllSlots();
		
		return new ResponseEntity<List<Slot>>(allSlots, HttpStatus.OK);
	}
	
	@PostMapping("/multiple/create")
	public ResponseEntity<Response> createMultipleSlotsHandler(@RequestBody List<CreateSlotRequest> requests ) {
		
		for(CreateSlotRequest request : requests) {
			slotService.createSlot(request);
		}
		
		Response response = new Response();
		response.setStatus(true);
		response.setMessage("Multiple slots created successfully !");
		
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

}
