package com.parking.Service;

import com.parking.Models.Review;
import com.parking.Request.CreateReviewRequest;

public interface ReviewService {
	
	// Create Review
	public Review createReview(CreateReviewRequest request, Long userId);

}
