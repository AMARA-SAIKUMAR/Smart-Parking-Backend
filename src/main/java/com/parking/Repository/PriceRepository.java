package com.parking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.parking.Models.Prices;

@Repository
public interface PriceRepository extends JpaRepository<Prices, Long> {
	
	// Get two wheeler Price
	@Query( nativeQuery = true ,value="SELECT two_Wheeler_Price FROM ( SELECT *, ROW_NUMBER() OVER (ORDER BY ID DESC) AS RN FROM PRICES ) AS SUBQUERY WHERE RN = 1")
	public Integer getTwoWheelerPrice();
	
	// Get three wheeler Price
	@Query( nativeQuery = true ,value="SELECT three_Wheeler_Price FROM ( SELECT *, ROW_NUMBER() OVER (ORDER BY ID DESC) AS RN FROM PRICES ) AS SUBQUERY WHERE RN = 1")
	public Integer getThreeWheelerPrice();
	
	// Get four wheeler Price
	@Query( nativeQuery = true ,value="SELECT four_Wheeler_Price FROM ( SELECT *, ROW_NUMBER() OVER (ORDER BY ID DESC) AS RN FROM PRICES ) AS SUBQUERY WHERE RN = 1")
	public Integer getFourWheelerPrice();
	
	// Update two wheeler Price
	// Update three wheeler Price
	// Update four wheeler Price
	
}
