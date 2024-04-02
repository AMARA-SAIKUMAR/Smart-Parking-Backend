package com.parking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parking.Models.Slot;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {

}
