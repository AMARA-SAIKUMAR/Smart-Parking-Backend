package com.parking.Service;

import com.parking.Models.Prices;
import com.parking.Request.UpdatePricesRequest;

public interface PricesService {
	
	public Integer getTwoWheelerParkingPrice();
	
	public Integer getThreeWheelerParkingPrice();
	
	public Integer getFourWheelerParkingPrice();
	
	public Prices updateParkingPrices(UpdatePricesRequest request);
	


}
