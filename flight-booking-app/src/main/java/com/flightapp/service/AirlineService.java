package com.flightapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.entity.Flight;
import com.flightapp.repository.FlightRepository;
import com.flightapp.request.AddFlightRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AirlineService {

	private static final Logger log = LoggerFactory.getLogger(AirlineService.class);
	@Autowired
	private FlightRepository flightRepository;

	public void addFlight(AddFlightRequest request) {

		Flight newFlight = new Flight();

		newFlight.setFlightNumber(request.getFlightNumber());
		newFlight.setAirlineName(request.getAirlineName());
		newFlight.setDepartingAirport(request.getDepartingAirport());
		newFlight.setArrivalAirport(request.getArrivalAirport());

		newFlight.setDepartureTime(request.getDepartureTime());
		newFlight.setArrivalTime(request.getArrivalTime());

		newFlight.setPrice(request.getPrice());
		newFlight.setTotalSeats(request.getTotalSeats());
		newFlight.setAvailableSeats(request.getAvailableSeats());

		newFlight.setTravelClass(request.getTravelClass());
		newFlight.setNonStop(request.isNonStop());

		flightRepository.save(newFlight);

	}
}
