package com.flightapp.entity;

import java.util.List;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotBlank(message = "PNR is required")
	private String pnr;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
    private String emailId;
    
    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$",
             message = "Invalid 10-digit Indian mobile number")
    private String contactNumber;

    @NotNull(message = "Number of seats required")
    @Min(1)
    private int numberOfSeats;
    
    private LocalDateTime bookingTimestamp = LocalDateTime.now();
    
    @NotNull(message = "Flight ID is required")
    private int flightId;
    
    private float price;
    private float totalPrice; 
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id")
    private List<Passenger> passengers;

}
