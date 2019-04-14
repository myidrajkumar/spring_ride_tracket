package com.rajkumar.techgig.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajkumar.techgig.model.Ride;
import com.rajkumar.techgig.repository.RideRepository;

@Service("rideService")
public class RideServiceImpl implements RideService {
	
	@Autowired
	private RideRepository rideRepository;

	@Override
	public List<Ride> getRides() {
		return rideRepository.getRides();
	}
	
	@Override
	public Ride createRide(Ride ride) {
		return rideRepository.createRide(ride);
	}
	
	@Override
	public Ride getRide(Integer id) {
		return rideRepository.getRide(id);
	}
	
	@Override
	public Ride updateRide(Ride ride) {
		return rideRepository.updateRide(ride);
	}
	
	@Override
	public void batch() {
        List<Ride> rides = getRides();		
        
        List<Object[]> pairs = rides.stream()
            .map(eachRide -> new Object[] {new Date(), eachRide.getId()})
            .collect(Collectors.toList());
        rideRepository.updateRides(pairs);
	}
	
	@Override
	public void deleteRide(Integer id) {
        rideRepository.deleteRide(id);		
	}

}
