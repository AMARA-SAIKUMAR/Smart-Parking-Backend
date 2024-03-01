package com.parking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parking.Models.Prices;

public interface PricesRepository extends JpaRepository<Prices, Integer> {

}
