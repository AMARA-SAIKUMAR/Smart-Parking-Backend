package com.parking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.Models.Prices;
import com.parking.Repository.PriceRepository;
import com.parking.Request.UpdatePricesRequest;

@Service
public class PricesServiceImplementation implements PricesService {
	
	
	@Autowired
	private PriceRepository priceRepository;

	@Override
	public Integer getTwoWheelerParkingPrice() {

		return priceRepository.getTwoWheelerPrice() ;

	}

	@Override
	public Integer getThreeWheelerParkingPrice() {
		
		return priceRepository.getThreeWheelerPrice();
	
	}

	@Override
	public Integer getFourWheelerParkingPrice() {
		
		return priceRepository.getFourWheelerPrice();
	
	}



	@Override
	public Prices updateParkingPrices(UpdatePricesRequest request) {
		
		Prices newPrices = new Prices();
		newPrices.setFourWheelerPrice(request.getFourWheelerPrice());
		newPrices.setThreeWheelerPrice(request.getThreeWheelerPrice());
		newPrices.setTwoWheelerPrice(request.getTwoWheelerPrice());
		
		Prices updatedPrice = priceRepository.save(newPrices);
		
		return updatedPrice;
	}

}
