package com.flightapp.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.flightapp.entity.Flight;

public interface FlightRepository extends CrudRepository<Flight, Integer> {
	List<Flight> findByDepartingAirportAndArrivalAirportAndDepartureTimeBetween(
			String departingAirport,
			String arrivalAirport,
			LocalDateTime departTime,
			LocalDateTime arrivalTime
	);
}
