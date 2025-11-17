package com.flightapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.request.AddFlightRequest;
import com.flightapp.service.AirlineService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1.0/flight/airline")
public class AirlineController {

	private static final Logger log = LoggerFactory.getLogger(AirlineController.class);

	@Autowired
	private AirlineService airlineService;

	@PostMapping("/inventory/add")
	public ResponseEntity<String> addFlightInventory(@RequestBody AddFlightRequest flightRequest) {

		log.info("Received request to add new flight: {}", flightRequest.getFlightNumber());

		airlineService.addFlight(flightRequest);
		String successMessage = "Flight inventory updated successfully for " + flightRequest.getFlightNumber();

		return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
	}
}
