package com.flightapp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.entity.Booking;
import com.flightapp.entity.Flight;
import com.flightapp.entity.Passenger;
import com.flightapp.repository.BookingRepository;
import com.flightapp.repository.FlightRepository;
import com.flightapp.request.BookingRequest;
import com.flightapp.request.PassengerRequest;
import com.flightapp.response.BookingResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookingService {
	@Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;
    
    @Autowired
    private PnrGeneratorService pnrGeneratorService;
    
    public BookingResponse bookFlight(int flightId, BookingRequest request) {
    	
    	Flight flight = flightRepository.findById(flightId)
    			.orElseThrow(() -> {
                    log.error("Flight not found for flightId={}", flightId);
                    return new RuntimeException("No flights found");
                });
    	
    	log.debug("Fetched flight details: {}", flight);
    	
    	if(flight.getAvailableSeats() < request.getNumberOfSeats()) {
    		throw new RuntimeException("Enough seats are not available");
    	}
    	
    	log.info("Seat availability validated. We can now proceed with the booking");
    	
    	float price = flight.getPrice();
    	float tax = price*0.18f;
    	float totalPrice = (price+tax) * request.getNumberOfSeats();
    	
    	String pnr = pnrGeneratorService.generatePnr();
    	
    	Booking booking = new Booking();
    	
    	booking.setPnr(pnr);
    	
        booking.setEmailId(request.getEmailId());
        booking.setContactNumber(request.getContactNumber());
        booking.setBookingTimestamp(LocalDateTime.now());
        booking.setNumberOfSeats(request.getNumberOfSeats());
        
        List<Passenger> passengerDetails = new ArrayList<>();

        for (PassengerRequest pr : request.getPassengers()) {
            Passenger passenger = new Passenger();
            passenger.setPassengerName(pr.getPassengerName());
            passenger.setAge(pr.getAge());
            passenger.setGender(pr.getGender());
            passenger.setSeatNum(pr.getSeatNum());
            passenger.setMealPref(pr.getMealPref());
            passengerDetails.add(passenger);
        }

        booking.setPassengers(passengerDetails);

        booking.setFlightId(flightId);

        booking.setPrice(price);
        booking.setTotalPrice(totalPrice);
    	
        log.debug("Booking object created: {}", booking);
        
        
        flight.setAvailableSeats(flight.getAvailableSeats() - request.getNumberOfSeats());
    
        flightRepository.save(flight);
        
        bookingRepository.save(booking);
        
        BookingResponse response = new BookingResponse();
        response.setPnr(pnr);
        response.setTotalPrice(totalPrice);
        response.setMessage("You have successfully booked the flight");

        return response;
    	
    }
    
    public Booking getBookingByPnr(String pnr) {
    	return bookingRepository.findByPnr(pnr)
    			.orElseThrow(() -> new RuntimeException("Booking not found for PNR: " + pnr));
    }

    public List<Booking> getBookingHistoryByEmailId(String emailId) {
        return bookingRepository.findByEmailId(emailId);
    }
    
    public String cancelBooking(String pnr) {
        Booking booking = bookingRepository.findByPnr(pnr)
                .orElseThrow(() -> new RuntimeException("PNR not found"));

        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);

        return "Booking cancelled successfully for PNR: " + pnr;
    }


}
