package com.parking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.Request.UpdatePricesRequest;
import com.parking.Response.PricesResponse;
import com.parking.Service.PricesService;

import jakarta.persistence.criteria.CriteriaBuilder.Case;

@RestController
@RequestMapping("/api/prices")
public class PriceController {
	
	@Autowired
	private PricesService pricesService;
	
	@GetMapping("/")
	public ResponseEntity<PricesResponse> getPricesHandler() {
		
		Integer twoWheelerPrice = pricesService.getTwoWheelerParkingPrice();
		
		Integer threeWheelerParkingPrice = pricesService.getThreeWheelerParkingPrice();
		
		Integer fourWheelerParkingPrice = pricesService.getFourWheelerParkingPrice();
		
		PricesResponse prices = new PricesResponse();
		prices.setTwoWheelerPrice(twoWheelerPrice);
		prices.setThreeWheelerPrice(threeWheelerParkingPrice);
		prices.setFourWheelerPrice(fourWheelerParkingPrice);
		
		return new ResponseEntity<PricesResponse>(prices, HttpStatus.OK);
		
	}
	
	@GetMapping("/{type}")
	public ResponseEntity<Integer> getPriceByTypeHandler(@PathVariable String type) {
		
		int price = pricesService.getPriceByType(type);
		return new ResponseEntity<Integer>(price, HttpStatus.OK);
			
	}
	


}
