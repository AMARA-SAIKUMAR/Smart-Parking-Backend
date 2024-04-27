package com.parking.Controller;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.Models.Order;
import com.parking.Repository.OrderRepository;
import com.parking.Response.PaymentLinkResponse;
import com.parking.Response.Response;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/api")
public class PaymentController {
	
	@Value("${razorpay.api.key}")
	String apiKey;
	
	@Value("${razorpay.api.secret}")
	String apiSecret;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@PostMapping("/payments/{orderId}")
	public ResponseEntity<PaymentLinkResponse> createPaymentLink(@PathVariable Long orderId ) throws RazorpayException {
		
		Optional<Order> findById = orderRepository.findById(orderId);
		Order order = findById.get();
		System.out.println("OrderId - "+ order.getId());
		System.out.println("Ortder Amount = " + order.getAmount());
		try {
			
			RazorpayClient razorpay = new RazorpayClient(apiKey, apiSecret);
			
			JSONObject paymentLinkRequest = new JSONObject();
			
			paymentLinkRequest.put("amount", order.getAmount() * 100); // The amount should be in paise
			paymentLinkRequest.put("currency", "INR");
		
			paymentLinkRequest.put("callback_url", "http://localhost:3000/payment/"+orderId);
			paymentLinkRequest.put("callback_method", "get");
			
			PaymentLink payment = razorpay.paymentLink.create(paymentLinkRequest);
			
			String paymentLinkId = payment.get("id");
			String paymentLinkUrl = payment.get("short_url");
			  
			PaymentLinkResponse response = new PaymentLinkResponse();
			
			response.setPayment_link_id(paymentLinkId);
			response.setPayment_link_url(paymentLinkUrl);
			System.out.println("Payment link id - " + response.getPayment_link_id());
			System.out.println("Payment link url - " + response.getPayment_link_url());
			return new ResponseEntity<PaymentLinkResponse>(response, HttpStatus.CREATED);
			
			
		} catch (Exception e) {
			
			throw new RazorpayException(e.getMessage());
		}
	}
	
	@GetMapping("/payments")
	public ResponseEntity<Response> redirect(@RequestParam(name = "payment_id") String paymentId, 
					@RequestParam(name = "order_id") Long orderId) throws RazorpayException {
		
		Optional<Order> findById = orderRepository.findById(orderId);
		Order order = findById.get();
		
		RazorpayClient razorpay = new RazorpayClient(apiKey, apiSecret);
		Response response = new Response();
		
		try {
			Payment payment = razorpay.payments.fetch(paymentId);
			
			if(payment.get("status").equals("captured")) {
				order.setPaymentStatus("Paid");
				orderRepository.save(order);
				
				response.setMessage("Your order got placed");
			}
		} catch (Exception e) {
			
			throw new RazorpayException(e.getMessage());
		}
		
		return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
	}
	

}
