package com.parking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parking.Models.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
