package com.flightapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "passengers")
@Data
public class Passenger {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

	@NotBlank(message = "Passenger Name required")
    private String passengerName;
	
	@NotNull(message = "Passenger age required")
    private int age;
	
	@NotBlank(message = "Gender is required")
    @Pattern(regexp = "Male|Female|Other", message = "Gender must be Male, Female or Other")
    private String gender;
	
	@NotBlank(message = "Seat number is required")
    @Pattern(regexp = "^[A-Z]\\d{1,2}$", message = "Seat number must be like A1, B12, C5")
    private String seatNum;
	
	@NotBlank(message = "Meal preference is required")
	@Pattern(regexp = "Veg|Non-Veg|No-Meal",
	         message = "Meal preference must be Veg, Non-Veg or No-Meal")
	private String mealPref;

    
}
