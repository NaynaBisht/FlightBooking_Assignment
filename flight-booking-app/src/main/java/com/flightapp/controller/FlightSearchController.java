package com.flightapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.request.FlightSearchRequest;
import com.flightapp.response.FlightSearchResponse;
import com.flightapp.service.FlightService;

@RestController
@RequestMapping("api/v1.0/flight")
public class FlightSearchController {

	@Autowired
	private FlightService flightService;
	
	@PostMapping("/search")
	public ResponseEntity<FlightSearchResponse> searchFlights(@RequestBody FlightSearchRequest request){
		FlightSearchResponse response = flightService.searchFlights(request);
		return ResponseEntity.ok(response);
	}
}
