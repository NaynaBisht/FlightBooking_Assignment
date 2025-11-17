package com.flightapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.request.BookingRequest;
import com.flightapp.response.BookingResponse;
import com.flightapp.service.BookingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1.0/flight/booking")
public class BookingController {

	@Autowired
    private BookingService bookingService;
	
	@PostMapping("/{flightId}")
    public ResponseEntity<BookingResponse> bookFlight(@PathVariable int flightId, @RequestBody BookingRequest request) {

		log.debug("Booking request payload: {}", request);
		
        BookingResponse response = bookingService.bookFlight(flightId, request);
        log.info("Booking successful for flightId={}, PNR={}", flightId, response.getPnr());

        return ResponseEntity.ok(response);
    }
}
