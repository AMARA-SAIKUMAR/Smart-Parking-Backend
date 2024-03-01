package com.parking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.Models.Revenue;
import com.parking.Repository.RevenueRepository;


@Service
public class RevenueServiceImplementation implements RevenueService {
	
	@Autowired
	private RevenueRepository revenueRepository;

	@Override
	public int getTwoWheelerRevenue() {
		
		Revenue revenue = new Revenue();
//		revenue.setTotalRevenue();
//		revenue.setTwoWheelerRevenue(0);
		return 0;
	}

	@Override
	public int getThreeWheelerRevenue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFourWheelerRevenue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateTwoWheelerRevenue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateThreeWheelerRevenue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateFourWheelerRevenue() {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
