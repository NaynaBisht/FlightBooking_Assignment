package com.flightapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.flightapp.entity.Booking;

public interface BookingRepository extends CrudRepository<Booking, Integer> {
	Booking findByPnr(String pnr);
}
