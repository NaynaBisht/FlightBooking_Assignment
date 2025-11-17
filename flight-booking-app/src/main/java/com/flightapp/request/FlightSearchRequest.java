package com.flightapp.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class FlightSearchRequest {

	@NotBlank(message = "Departing airport is required")
	private String departingAirport;
	
	@NotBlank(message = "Arrival airport is required")
	private String arrivalAirport;
	
	@NotBlank(message = "Departure date is required")
	private LocalDate departDate;
	
	private LocalDate returnDate;
	
	@NotBlank(message = "Trip type is required")
    private String tripType = "ONE_WAY";
	
    private PassengerCount passengers;
	
	public void setPassengers(PassengerCount passengers) {
		this.passengers = passengers;
	}

	public String getDepartingAirport() {
		return departingAirport;
	}

	public void setDepartingAirport(String departingAirport) {
		this.departingAirport = departingAirport;
	}

	public String getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public LocalDate getDepartDate() {
		return departDate;
	}

	public void setDepartDate(LocalDate departDate) {
		this.departDate = departDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public String getTripType() {
		return tripType;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}

	public String getTravelClass() {
		return travelClass;
	}

	public void setTravelClass(String travelClass) {
		this.travelClass = travelClass;
	}

	private String travelClass;
	
	
}
