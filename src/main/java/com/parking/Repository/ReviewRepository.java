package com.parking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parking.Models.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
