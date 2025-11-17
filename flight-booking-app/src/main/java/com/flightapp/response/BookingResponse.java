package com.flightapp.response;

import lombok.Data;

@Data
public class BookingResponse {
	private String pnr;
    private float totalPrice;
    private String message;
}
