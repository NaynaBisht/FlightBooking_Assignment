package com.flightapp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.entity.Flight;
import com.flightapp.repository.FlightRepository;
import com.flightapp.request.FlightSearchRequest;
import com.flightapp.response.FlightSearchResponse;
import com.flightapp.response.FlightSearchResponse.FlightInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FlightService {
	
	@Autowired
	private FlightRepository flightRepository;
	
	public FlightSearchResponse searchFlights (FlightSearchRequest request) {
		
		log.debug("Search request : {}", request);
		
		LocalDateTime startTime = request.getDepartDate().atStartOfDay();
		LocalDateTime endTime = request.getDepartDate().atTime(23,59,59);
		
		int passengerCount = request.getPassengers().getTotalPassengers();
		
		List<Flight> flights = flightRepository
		        .findByDepartingAirportAndArrivalAirportAndDepartureTimeBetween(
		                request.getDepartingAirport(),
		                request.getArrivalAirport(),
		                startTime,
		                endTime
		);

		List<FlightInfo> results = new ArrayList<>();
		
		for(Flight flight : flights) {
			if(flight.getAvailableSeats() >= passengerCount) {
				
				FlightInfo info = new FlightInfo();
				
				info.setFlightId(flight.getId());
				info.setFlightNumber(flight.getFlightNumber());
				info.setAirlineName(flight.getAirlineName());
                info.setDepartingAirport(flight.getDepartingAirport());
                info.setArrivalAirport(flight.getArrivalAirport());
                
                info.setDepartureTime(flight.getDepartureTime().toString());
                info.setArrivalTime(flight.getArrivalTime().toString());
                
                long minutes = java.time.Duration.between(flight.getDepartureTime(), flight.getArrivalTime()).toMinutes();
                
                info.setDuration(minutes + "minutes");
				
                info.setPrice(flight.getPrice());
                info.setTotalPrice(flight.getPrice() * passengerCount);
                
                info.setAvailableSeats(flight.getAvailableSeats());
                info.setTravelClass(flight.getTravelClass());
                
                info.setNonstop(flight.isNonStop());
                
                results.add(info);
                
			}
		}
		
		FlightSearchResponse response = new FlightSearchResponse();
        response.setTotalFlights(results.size());
        
        response.setMessage(results.isEmpty() 
                ? "No flights found for your search criteria"
                : "Success");

        response.setFlights(results);

       
        return response;
	}
}
