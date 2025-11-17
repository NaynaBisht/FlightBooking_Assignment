package com.flightapp.request;

import java.util.List;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookingRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String emailId;

    @NotBlank(message = "Contact number is required")
    @Pattern(
        regexp = "^[6-9]\\d{9}$",
        message = "Contact number must be a valid 10-digit Indian mobile number"
    )
    private String contactNumber;

    @NotNull(message = "Number of seats is required")
    @Min(1)
    private Integer numberOfSeats;

    @NotBlank(message = "Meal preference is required")
    @Pattern(
        regexp = "Veg|Non-Veg|No-Meal",
        message = "Meal preference must be Veg, Non-Veg, or No-Meal"
    )
    private String mealPref;

    @NotNull(message = "Passenger list is required")
    @Size(min = 1, message = "At least one passenger is required")
    private List<PassengerRequest> passengers;
}
