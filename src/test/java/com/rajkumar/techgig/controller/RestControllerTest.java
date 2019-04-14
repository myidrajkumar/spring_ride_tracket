package com.rajkumar.techgig.controller;

import java.net.URI;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.rajkumar.techgig.model.Ride;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RestControllerTest {
	
	@Test
	public void testGetRides() {
		Assertions.assertTimeout(Duration.ofSeconds(5), () -> {
			RestTemplate restTemplate = new RestTemplate();
			
			RequestEntity requestEntity = RequestEntity
					.get(new URI("http://localhost:8090/rides"))
					.build();
			
			ResponseEntity<List<Ride>> responseEntity = restTemplate.exchange(
					 requestEntity,
					 new ParameterizedTypeReference<List<Ride>>() {});
			
			List<Ride> rides = responseEntity.getBody();

			for (Ride ride : rides) {
				System.out.println("Ride name: " + ride);
			}

			responseEntity.getBody().stream().forEach(System.out::println);
		});
	}
	
	@Test
	public void testCreateRide() {
		Assertions.assertTimeout(Duration.ofSeconds(5), () -> {
			RestTemplate restTemplate = new RestTemplate();
			
			Ride ride = new Ride();
			ride.setName("Third Ride");
			ride.setDuration(40000);
			
			ride = restTemplate.postForObject(new URI("http://localhost:8090/rides"), ride, Ride.class);
			
	    });
	}
	
	@Test
	public void testGetRide() {
		RestTemplate restTemplate = new RestTemplate();
		Ride ride = restTemplate.getForObject("http://localhost:8090/rides/1", Ride.class);
		Assertions.assertEquals("First Ride", ride.getName());
	}
	
	@Test
	public void testUpdateRide() {
		RestTemplate restTemplate = new RestTemplate();
		
		//As first step, we will initialize duration to specific value
		Ride ride = restTemplate.getForObject("http://localhost:8090/rides/1", Ride.class);
		Assertions.assertEquals("First Ride", ride.getName());
		
		ride.setDuration(10000);
		restTemplate.put("http://localhost:8090/rides", ride);
		ride = restTemplate.getForObject("http://localhost:8090/rides/1", Ride.class);
		Assertions.assertEquals(10000, ride.getDuration());
		
		ride.setDuration(ride.getDuration() + 20);
		restTemplate.put("http://localhost:8090/rides", ride);
		ride = restTemplate.getForObject("http://localhost:8090/rides/1", Ride.class);
		Assertions.assertEquals(10020, ride.getDuration());
		
	}
	
	@Test
	public void testBatchUpdateRides() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForObject("http://localhost:8090/batch", Object.class);
	}
	
	@Test
	public void testDeleteRide() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete("http://localhost:8090/rides/3");
	}

}
