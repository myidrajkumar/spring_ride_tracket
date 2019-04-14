package com.rajkumar.techgig.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rajkumar.techgig.model.Ride;
import com.rajkumar.techgig.service.RideService;

@Controller
public class RideController {
	
	@Autowired
	private RideService rideService;
	
	@RequestMapping(value = "/rides")
	public @ResponseBody List<Ride> getRides() {
		return rideService.getRides();
	}
	
	@RequestMapping(value = "/rides", method = RequestMethod.POST)
	public @ResponseBody Ride createRide(@RequestBody Ride ride) {
		return rideService.createRide(ride);
	}
	
	@RequestMapping(value = "/rides/{id}")
	public @ResponseBody Ride getRide(@PathVariable("id") Integer id) {
		return rideService.getRide(id);
	}
	
	@RequestMapping(value = "/rides", method = RequestMethod.PUT)
	public @ResponseBody Ride updateRide(@RequestBody Ride ride) {
		return rideService.updateRide(ride);
	}
	
	@RequestMapping(value = "/batch")
	public @ResponseBody Ride batch() {
		rideService.batch();
		return null;
	}
	
	@RequestMapping(value = "/rides/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Object deleteRide(@PathVariable("id") Integer id) {
		rideService.deleteRide(id);
		return null;
	}

}
