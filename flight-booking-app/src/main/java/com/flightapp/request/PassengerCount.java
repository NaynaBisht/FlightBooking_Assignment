package com.flightapp.request;

import jakarta.validation.constraints.Min;

public class PassengerCount {
	
	@Min(1)
	private int adults;
	
	public int getAdults() {
		return adults;
	}

	public void setAdults(int adults) {
		this.adults = adults;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}

	@Min(0)
	private int children;

}
