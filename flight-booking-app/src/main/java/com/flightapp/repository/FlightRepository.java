package com.flightapp.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.flightapp.entity.Flight;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Integer> {
	List<Flight> findByDepartingAirportAndArrivalAirportAndDepartureTimeBetween(
			String departingAirport,
			String arrivalAirport,
			LocalDateTime departTime,
			LocalDateTime arrivalTime
	);
}
