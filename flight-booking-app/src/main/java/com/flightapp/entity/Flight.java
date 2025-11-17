package com.flightapp.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Flights")
public class Flight {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

	@NotBlank(message = "Flight number is required")
    private String flightNumber;
	
	@NotBlank(message = "Airline name is required")
    private String airlineName;
	
	@NotBlank(message = "Departing airport is required")
    private String departingAirport;
	
	@NotBlank(message = "Arrival airport is required")
    private String arrivalAirport;
	
	@NotNull
    private LocalDateTime departureTime;
	
	@NotNull
    private LocalDateTime arrivalTime;
	
	@Min(value = 100, message = "Price must be greater than 100")
    private float price;
	
	@Min(value = 5, message = "Total seats must be greater than 4")
	private int totalSeats;
	
	 @Min(value = 0, message = "Available seats cannot be negative")
	 private int availableSeats;
	 
	 public int getId() {
		return id;
	}

	 public void setId(int id) {
		 this.id = id;
	 }

	 public String getFlightNumber() {
		 return flightNumber;
	 }

	 public void setFlightNumber(String flightNumber) {
		 this.flightNumber = flightNumber;
	 }

	 public String getAirlineName() {
		 return airlineName;
	 }

	 public void setAirlineName(String airlineName) {
		 this.airlineName = airlineName;
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

	 public LocalDateTime getDepartureTime() {
		 return departureTime;
	 }

	 public void setDepartureTime(LocalDateTime departureTime) {
		 this.departureTime = departureTime;
	 }

	 public LocalDateTime getArrivalTime() {
		 return arrivalTime;
	 }

	 public void setArrivalTime(LocalDateTime arrivalTime) {
		 this.arrivalTime = arrivalTime;
	 }

	 public float getPrice() {
		 return price;
	 }

	 public void setPrice(float price) {
		 this.price = price;
	 }

	 public int getTotalSeats() {
		 return totalSeats;
	 }

	 public void setTotalSeats(int totalSeats) {
		 this.totalSeats = totalSeats;
	 }

	 public int getAvailableSeats() {
		 return availableSeats;
	 }

	 public void setAvailableSeats(int availableSeats) {
		 this.availableSeats = availableSeats;
	 }

	 public String getTravelClass() {
		 return travelClass;
	 }

	 public void setTravelClass(String travelClass) {
		 this.travelClass = travelClass;
	 }

	 public String getFareType() {
		 return fareType;
	 }

	 public void setFareType(String fareType) {
		 this.fareType = fareType;
	 }

	 public boolean isNonStop() {
		 return nonStop;
	 }

	 public void setNonStop(boolean nonStop) {
		 this.nonStop = nonStop;
	 }

	 public boolean isRefundable() {
		 return refundable;
	 }

	 public void setRefundable(boolean refundable) {
		 this.refundable = refundable;
	 }

	 @NotNull
	 private String travelClass;
	 
	 private String fareType;
	 
	 private boolean nonStop;
	 
	 private boolean refundable;
	 
	 
}
