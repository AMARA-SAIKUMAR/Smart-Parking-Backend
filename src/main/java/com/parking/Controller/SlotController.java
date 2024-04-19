package com.parking.Controller;

import java.util.List;
import java.util.Optional;

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
import com.parking.Models.Order;
import com.parking.Models.Slot;
import com.parking.Repository.SlotRepository;
import com.parking.Request.BookSlotRequest;
import com.parking.Request.CreateSlotRequest;
import com.parking.Response.Response;
import com.parking.Service.SlotService;

@RestController
@RequestMapping("/api/slot")
public class SlotController {
	
	@Autowired
	private SlotService slotService;
	
	@Autowired
	private SlotRepository slotRepository;
	
	@GetMapping("/{slotId}")
	public ResponseEntity<Slot> findlotByIdHandler(@PathVariable Long slotId ) throws SlotException {
		Optional<Slot> findById = slotRepository.findById(slotId);
		if(findById.get() == null) {
			throw new SlotException("Slot not found with id - " + slotId);
		}
		
		Slot slot = findById.get();
		
		return new ResponseEntity<Slot>(slot, HttpStatus.OK);
	}
	
	@PostMapping("/book")
	public ResponseEntity<Order> bookSlotHandler(@RequestBody BookSlotRequest request) {
		
		Order order = slotService.bookSlot(request);
		
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Slot>> findAllSlots() {
		
		List<Slot> allSlots = slotService.findAllSlots();
		
		return new ResponseEntity<List<Slot>>(allSlots, HttpStatus.OK);
	}
	
	@PutMapping("/freeup/{slotId}")
	public ResponseEntity<Slot> freeUpSlotHandler(@PathVariable Long slotId)  {
		
		Slot slot = slotService.freeUpSlot(slotId);
		
		return new ResponseEntity<Slot>(slot, HttpStatus.OK);
	}

}
