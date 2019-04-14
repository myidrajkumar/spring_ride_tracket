package com.rajkumar.techgig.repository;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.rajkumar.techgig.model.Ride;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Ride> rowMapper = (resultSet, rowNumber) -> {
		Ride ride = new Ride();
		ride.setId(resultSet.getInt("id"));
		ride.setName(resultSet.getString("name"));
		ride.setDuration(resultSet.getInt("duration"));
		return ride;
	};
    
	@Override
	public List<Ride> getRides() {
		return jdbcTemplate.query("select * from ride", rowMapper);
	}
	
	@Override
	public Ride createRide(Ride ride) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		PreparedStatementCreator preparedStatementCreator = conn -> {
			PreparedStatement prepareStatement = 
					conn.prepareStatement(
							"insert into ride(name, duration) values(?, ?)",
							new String[] {"id"});
			prepareStatement.setString(1, ride.getName());
			prepareStatement.setInt(2, ride.getDuration());
			
			return prepareStatement;
		};
		jdbcTemplate.update(preparedStatementCreator, keyHolder);
		
		Number id = keyHolder.getKey();
		
		return getRide(id.intValue());
	}

	@Override
	public Ride getRide(Integer id) {
		System.out.println("Received ID " + id);
		return jdbcTemplate.queryForObject("select * from ride where id=?", rowMapper, id);
	}
	
	@Override
	public Ride updateRide(Ride ride) {
		jdbcTemplate.update("update ride set name=?, duration=? where id=?",
				ride.getName(), ride.getDuration(), ride.getId());
		return getRide(ride.getId());
	}
	
	@Override
	public void updateRides(List<Object[]> pairs) {
		jdbcTemplate.batchUpdate("update ride set ride_date=? where id=?", pairs);
	}
	
	@Override
	public void deleteRide(Integer id) {
        jdbcTemplate.update("delete from ride where id=?", id);		
	}

}
