package com.flightapp.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.flightapp.entity.Booking;
import com.flightapp.entity.Flight;
import com.flightapp.repository.BookingRepository;
import com.flightapp.repository.FlightRepository;
import com.flightapp.request.BookingRequest;
import com.flightapp.request.PassengerRequest;
import com.flightapp.response.BookingResponse;
import com.flightapp.service.BookingService;
import com.flightapp.service.PnrGeneratorService;

@SpringBootTest
class BookingServiceTest {

    @MockBean
    private BookingRepository bookingRepository;

    @MockBean
    private FlightRepository flightRepository;

    @MockBean
    private PnrGeneratorService pnrGeneratorService;

    @Autowired
    private BookingService bookingService;

    @Test
    void testBookFlightSuccess() {

        Flight flight = new Flight();
        flight.setId(1);
        flight.setAvailableSeats(10);
        flight.setPrice(2000);
        flight.setDepartureTime(LocalDateTime.now());
        flight.setArrivalTime(LocalDateTime.now().plusHours(2));

        when(flightRepository.findById(1)).thenReturn(Optional.of(flight));
        when(pnrGeneratorService.generatePnr()).thenReturn("PNR001");
        when(bookingRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        // Create booking request
        BookingRequest req = new BookingRequest();
        req.setEmailId("test@gmail.com");
        req.setContactNumber("9876543210");
        req.setNumberOfSeats(2);
        req.setMealPref("Veg");

        PassengerRequest p = new PassengerRequest();
        p.setPassengerName("John");
        p.setAge(25);
        p.setGender("Male");
        p.setSeatNum("A1");
        p.setMealPref("Veg");

        req.setPassengers(List.of(p));

        // Call service
        BookingResponse response = bookingService.bookFlight(1, req);

        // Assertions
        assertEquals("PNR001", response.getPnr());
        assertTrue(response.getTotalPrice() > 0);
        assertEquals("You have successfully booked the flight", response.getMessage());

        verify(bookingRepository, times(1)).save(any());
        verify(flightRepository, times(1)).save(any());
    }

    @Test
    void testBookFlight_FlightNotFound() {

        when(flightRepository.findById(99)).thenReturn(Optional.empty());

        BookingRequest req = new BookingRequest();
        req.setEmailId("test@gmail.com");
        req.setContactNumber("9876543210");
        req.setNumberOfSeats(1);
        req.setPassengers(List.of(new PassengerRequest()));

        RuntimeException ex = assertThrows(RuntimeException.class, 
                () -> bookingService.bookFlight(99, req));

        assertEquals("No flights found", ex.getMessage());
    }

    @Test
    void testBookFlight_NotEnoughSeats() {

        Flight flight = new Flight();
        flight.setAvailableSeats(1);

        when(flightRepository.findById(5)).thenReturn(Optional.of(flight));

        BookingRequest req = new BookingRequest();
        req.setEmailId("test@gmail.com");
        req.setContactNumber("9876543210");
        req.setNumberOfSeats(5);
        req.setPassengers(List.of(new PassengerRequest()));

        RuntimeException ex = assertThrows(RuntimeException.class, 
                () -> bookingService.bookFlight(5, req));

        assertEquals("Enough seats are not available", ex.getMessage());
    }

    @Test
    void testGetBookingByPnr() {

        Booking b = new Booking();
        b.setPnr("PNR123");

        when(bookingRepository.findByPnr("PNR123")).thenReturn(Optional.of(b));

        Booking result = bookingService.getBookingByPnr("PNR123");

        assertEquals("PNR123", result.getPnr());
    }

    @Test
    void testGetBookingHistory() {

        Booking b = new Booking();
        b.setEmailId("abc@gmail.com");

        when(bookingRepository.findByEmailId("abc@gmail.com")).thenReturn(List.of(b));

        List<Booking> list = bookingService.getBookingHistoryByEmailId("abc@gmail.com");

        assertEquals(1, list.size());
    }

    @Test
    void testCancelBooking() {

        Booking b = new Booking();
        b.setPnr("P001");

        when(bookingRepository.findByPnr("P001")).thenReturn(Optional.of(b));

        String result = bookingService.cancelBooking("P001");

        assertEquals("Booking cancelled successfully for PNR: P001", result);
        assertEquals("CANCELLED", b.getStatus());

        verify(bookingRepository, times(1)).save(b);
    }
}
