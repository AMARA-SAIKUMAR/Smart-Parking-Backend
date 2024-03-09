package com.parking.Service;

import java.util.List;

import com.parking.Models.Review;
import com.parking.Request.CreateReviewRequest;

public interface ReviewService {
	
	// Create Review
	public Review createReview(CreateReviewRequest request, Long userId);
	
	public List<Review> findAllReviews();

}
