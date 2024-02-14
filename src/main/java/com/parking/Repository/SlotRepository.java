package com.parking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parking.Models.Slot;

public interface SlotRepository extends JpaRepository<Slot, Long> {

}
