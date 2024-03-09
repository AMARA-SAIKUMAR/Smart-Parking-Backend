package com.parking.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.Models.Review;
import com.parking.Repository.ReviewRepository;
import com.parking.Request.CreateReviewRequest;

@Service
public class ReviewServiceImplementation implements ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	public Review createReview(CreateReviewRequest request, Long userId) {
		
		Review newReview = new Review();
		
		newReview.setRating(request.getRating());
		newReview.setReview(request.getReview());
		newReview.setUserId(userId);
		
		return reviewRepository.save(newReview);
	}

	@Override
	public List<Review> findAllReviews() {
		
		List<Review> allReviews = reviewRepository.findAll();
		
		return allReviews;
	}

}
