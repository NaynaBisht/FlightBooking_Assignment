package com.flightapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.entity.Booking;
import com.flightapp.request.BookingRequest;
import com.flightapp.response.BookingResponse;
import com.flightapp.service.BookingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1.0/flight")
public class BookingController {

	@Autowired
    private BookingService bookingService;
	
	@GetMapping("/ticket/{pnr}")
	public ResponseEntity<Booking> getTicketDetailsByPnr(@PathVariable String pnr) {
	    
		log.info("Fetching ticket details for PNR={}", pnr);
		
		Booking booking = bookingService.getBookingByPnr(pnr);
		
		log.debug("Ticket details retrieved for PNR={}", pnr);

	    return ResponseEntity.ok(booking);
	}
	
	@GetMapping("/booking/history/{emailId}")
	public ResponseEntity<List<Booking>> getBookingHistory(@PathVariable String emailId) {
	    
		log.info("Fetching booking history for emailId={}", emailId);
		
		List<Booking> bookings = bookingService.getBookingHistoryByEmailId(emailId);
	    
		return ResponseEntity.ok(bookings);
	}

	
	@PostMapping("/booking/{flightId}")
    public ResponseEntity<BookingResponse> bookFlight(@PathVariable int flightId, @RequestBody BookingRequest request) {

		log.debug("Booking request payload: {}", request);
		
        BookingResponse response = bookingService.bookFlight(flightId, request);
        
        log.info("Booking successful for flightId={}, PNR={}", flightId, response.getPnr());

        return ResponseEntity.ok(response);
    }

	@DeleteMapping("/booking/cancel/{pnr}")
	public ResponseEntity<String> cancelBooking(@PathVariable String pnr) {
	    
		log.warn("Cancel request received for PNR={}", pnr);
		
		String response = bookingService.cancelBooking(pnr);
	    
		log.info("Booking cancelled successfully for PNR={}", pnr);
		
		return ResponseEntity.ok(response);
	}

}
