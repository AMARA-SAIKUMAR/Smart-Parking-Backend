package com.parking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.Exceptions.UserException;
import com.parking.Models.Review;
import com.parking.Models.User;
import com.parking.Request.CreateReviewRequest;
import com.parking.Service.ReviewService;
import com.parking.Service.UserService;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<Review> createReviewHandler(@RequestBody CreateReviewRequest request, 
			@RequestHeader("Authorization") String jwt) throws UserException {
		
		User user = userService.findUserProfileByJwt(jwt);
		
		Review createdReview = reviewService.createReview(request, user.getId());
		
		return new ResponseEntity<Review>(createdReview, HttpStatus.OK);
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Review>> findAllReviewHandler() {
		
		List<Review> allReviews = reviewService.findAllReviews();
		
		return new ResponseEntity<List<Review>>(allReviews, HttpStatus.OK);
	}

}
