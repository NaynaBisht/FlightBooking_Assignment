package com.flightapp.response;

import java.util.List;

import lombok.Data;

@Data
public class FlightSearchResponse {
	
	private int totalFlights;
	private String message;
	private List<FlightInfo> flights;
	
	@Data
	public static class FlightInfo{
		private int flightId;
        private String flightNumber;
        private String airlineName;
        private String departingAirport;
        private String arrivalAirport;

        private String departureTime;
        private String arrivalTime;
        private String duration;
        private float price;
        private float totalPrice;

        private int availableSeats;
        private String travelClass;
        private boolean nonstop;
	}
}
