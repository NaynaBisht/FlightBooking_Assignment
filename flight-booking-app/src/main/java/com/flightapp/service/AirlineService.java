package com.flightapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.entity.Flight;
import com.flightapp.repository.FlightRepository;
import com.flightapp.request.AddFlightRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AirlineService {

	@Autowired
	private FlightRepository flightRepository;

	public void addFlight(AddFlightRequest request) {
		
		log.info("Received request to add new flight: {}", request.getFlightNumber());

		Flight newFlight = new Flight();

		newFlight.setFlightNumber(request.getFlightNumber());
		newFlight.setAirlineName(request.getAirlineName());
		newFlight.setDepartingAirport(request.getDepartingAirport());
		newFlight.setArrivalAirport(request.getArrivalAirport());

		newFlight.setDepartureTime(request.getDepartureTime());
		newFlight.setArrivalTime(request.getArrivalTime());

		newFlight.setPrice(request.getPrice());
		newFlight.setTotalSeats(request.getTotalSeats());
		newFlight.setAvailableSeats(request.getTotalSeats());

		newFlight.setTravelClass(request.getTravelClass());
		newFlight.setNonStop(request.isNonStop());

		log.debug("Saving new flight: {}", newFlight);
		
		flightRepository.save(newFlight);

	}
}
