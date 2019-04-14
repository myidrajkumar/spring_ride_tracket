package com.rajkumar.techgig.service;

import java.util.List;

import com.rajkumar.techgig.model.Ride;

public interface RideService {

	List<Ride> getRides();

	Ride createRide(Ride ride);

	Ride getRide(Integer id);

	Ride updateRide(Ride ride);

	void batch();

	void deleteRide(Integer id);

}
