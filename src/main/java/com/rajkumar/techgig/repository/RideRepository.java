package com.rajkumar.techgig.repository;

import java.util.List;

import com.rajkumar.techgig.model.Ride;

public interface RideRepository {

	List<Ride> getRides();

	Ride createRide(Ride ride);

	Ride getRide(Integer id);

	Ride updateRide(Ride ride);

	void updateRides(List<Object[]> pairs);

	void deleteRide(Integer id);

}
