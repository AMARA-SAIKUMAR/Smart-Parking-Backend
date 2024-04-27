package com.parking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.Exceptions.SlotException;
import com.parking.Models.Prices;
import com.parking.Models.Slot;
import com.parking.Request.CreateSlotRequest;
import com.parking.Request.UpdatePricesRequest;
import com.parking.Response.PricesResponse;
import com.parking.Response.Response;
import com.parking.Service.PricesService;
import com.parking.Service.SlotService;

@RestController
@RequestMapping("/admin/")
public class AdminController {
	
	@Autowired
	private SlotService slotService;
	
	@Autowired
	private PricesService pricesService;
	
	@PostMapping("slot/create") 
	public ResponseEntity<Slot> createSlot(@RequestBody CreateSlotRequest req ) {
		
		Slot createSlot = slotService.createSlot(req.getWheelerType());
		
		return new ResponseEntity<Slot>(createSlot, HttpStatus.OK);
		
	}
	
	// Handler To create admins through Admin Account ??
	
	@DeleteMapping("slot/delete/{id}")
	public ResponseEntity<Response> deleteSlot(@PathVariable Long id) throws SlotException {
		
		Response response = slotService.deleteSlot(id);
		
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@GetMapping("slot/all")
	public ResponseEntity<List<Slot>> findAllSlots() {
		
		List<Slot> allSlots = slotService.findAllSlots();
		
		return new ResponseEntity<List<Slot>>(allSlots, HttpStatus.OK);
	}
	
	@PostMapping("slots/create")
	public ResponseEntity<Response> createMultipleSlotsHandler(@RequestBody CreateSlotRequest request ) {
		
		int numberOfSlots = request.getNoOfSlots();
		String wheelerType = request.getWheelerType();
		
		while(numberOfSlots-- > 0) {
			slotService.createSlot(wheelerType);
		}
		
		Response response = new Response();
		response.setStatus(true);
		response.setMessage("Multiple slots created successfully !");
		
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@GetMapping("slot")
	public ResponseEntity<List<Slot>> findSlotsByTypeHandler(@RequestParam String type) {
		
		List<Slot> slotsByType = slotService.findSlotsByType(type);
		
		return new ResponseEntity<List<Slot>>(slotsByType, HttpStatus.OK);
	}
	
	@PostMapping("prices/update")
	public ResponseEntity<PricesResponse> updatePricesHandler(@RequestBody UpdatePricesRequest request) {
		
		Prices updateParkingPrices = pricesService.updateParkingPrices(request);
		
		PricesResponse response = new PricesResponse();
		response.setTwoWheelerPrice(updateParkingPrices.getTwoWheelerPrice());
		response.setThreeWheelerPrice(updateParkingPrices.getThreeWheelerPrice());
		response.setFourWheelerPrice(updateParkingPrices.getFourWheelerPrice());
		
		return new ResponseEntity<PricesResponse>(response, HttpStatus.OK);
	}
	
 
	
	/*        Remaining Tasks in Project
	 *   
	 *   1) All the API's for Data Visualization need to be implemented --> All these can be done from frontEnd ✅
	 *   		i)  N.o vehicles parked in the last 4 months
	 *   		ii) Total Revenue 
	 *   		ii) Total Revenue of individual wheeler Type 
	 *   
	 *   2) Review Api is pending  ✅
	 *   3) Role based authentication  --> Will Ask Jeevan how to do it
	 *   4) Backend Code for razorpay ✅
	 *   5) FrontEnd for creating a review & Book slot success ?? 
	 */

}
