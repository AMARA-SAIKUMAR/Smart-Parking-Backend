package com.parking.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;
import org.hibernate.dialect.function.TruncFunction.DatetimeTrunc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import com.parking.Exceptions.SlotException;
import com.parking.Models.Order;
import com.parking.Models.Slot;
import com.parking.Models.User;
import com.parking.Models.Vehicle;
import com.parking.Repository.OrderRepository;
import com.parking.Repository.SlotRepository;
import com.parking.Request.BookSlotRequest;
import com.parking.Request.CreateSlotRequest;
import com.parking.Response.Response;

@Service
public class SlotServiceImplementation implements SlotService {
	
	@Autowired
	private SlotRepository slotRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private UserService userService;

	@Override
	public Slot createSlot(String wheelerType) {
		
		Slot createdSlot = new Slot();
		createdSlot.setOccupancyStatus("free");
		createdSlot.setSlotType(wheelerType);
//		createdSlot.setWheelerType(req.getWheelerType());
		
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

	@Override
	public Order bookSlot(BookSlotRequest req) {
		
		
		// create new vehicle
		Vehicle createVehicle = vehicleService.createVehicle(req.getVehicleNumber());
		
		// Book Slot
		Optional<Slot> optional = slotRepository.findById(req.getSlotId());
		Slot slot = optional.get();
		slot.setOccupancyStatus("occupied");
		
		// Parse the bookedTime from String to LocalTime
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
		LocalTime bookedTime = LocalTime.parse(req.getBookedTime(), formatter);
		slot.setBookedTime(bookedTime);
		slot.setParkHours(req.getParkHours());
		slotRepository.save(slot);
		
		// create order
		Order bookOrder = new Order();
		bookOrder.setDate(LocalDate.now());
		bookOrder.setWheelerType(slot.getSlotType());
//		bookOrder.setPaymentStatus(req.getPaymentStatus()); This is done by paymentController
		bookOrder.setAmount(req.getAmount());
		bookOrder.setSlotId(req.getSlotId());
//		bookOrder.setUser(user);
		Order savedOrder = orderRepository.save(bookOrder);
				
//		try {
//			freeUpSlot(req);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		// No need of saving the payment details because
		// the amount is already stored in the order 
		
		return savedOrder;
	}

	@Override
	public Slot freeUpSlot(Long slotId)  {
		
		Optional<Slot> optional = slotRepository.findById(slotId);
		Slot slot = optional.get();
		
//		int minutes = (int)req.getParkedHours()*60;		
//		// Execute the following code after some time
//		Thread.sleep(1000*60*minutes + 1000*60*15 );
		
		slot.setOccupancyStatus("free");
		slot.setParkHours(0);
		slot.setBookedTime(null);
		slotRepository.save(slot);
		
		
		return slot;
	}

	@Override
	public List<Slot> findSlotsByType(String slotType) {
			List<Slot> allSlots = findAllSlots();
			
			List<Slot> slotsByType = new ArrayList<>() ;
			
			for(Slot slot : allSlots ) {
				if(slot.getSlotType().equals(slotType)) {
					slotsByType.add(slot);
				}
			}
			
		return slotsByType;
	}	
	
}
