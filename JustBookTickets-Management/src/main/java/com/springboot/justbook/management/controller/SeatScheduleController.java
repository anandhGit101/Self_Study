/**
 * 
 */
package com.springboot.justbook.management.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.justbook.management.exception.SeatsMgmtException;
import com.springboot.justbook.management.service.SeatScheduleService;
import com.springboot.justbook.vo.MovieMgmtSeatScheduleVOResponseObject;
import com.springboot.justbook.vo.SeatScheduleVO;

import io.swagger.annotations.ApiOperation;

/**
 * @author M1006601
 *
 */
@RestController
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8899" }) 
public class SeatScheduleController {
	
	/**
	 * LOGGER Logger object for logging in different logger Levels.
	 */
	static Logger LOGGER = LoggerFactory.getLogger(SeatScheduleController.class);

	@Autowired
	SeatScheduleService seatSchService;
	
	@GetMapping(path = "/seatsschedule/booked", produces = "application/json")
	@ApiOperation(value = "Get Seat Occupied details filtered by Schedule Id", response=MovieMgmtSeatScheduleVOResponseObject.class)
	public ResponseEntity<MovieMgmtSeatScheduleVOResponseObject> getSeatsBookedByScheduleId(@RequestParam Long scheduleId) throws SeatsMgmtException{
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getSeatsBookedByScheduleId() method to fetch list of Seats of Scheduled Movie");
		ResponseEntity<MovieMgmtSeatScheduleVOResponseObject> responseObj = null;
		MovieMgmtSeatScheduleVOResponseObject seatScheduleResponse = seatSchService.findSeatScheduleByMovieScheduleId(scheduleId);
		responseObj = new ResponseEntity<MovieMgmtSeatScheduleVOResponseObject>(seatScheduleResponse, HttpStatus.OK);
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getSeatsBookedByScheduleId() method to fetch list of Movies");
		return responseObj;
	}
	
	
	@PostMapping(path = "/seatsschedule/booked", produces = "application/json")
	@ApiOperation(value = "Add newly Selected Seat details for the movie", response=MovieMgmtSeatScheduleVOResponseObject.class)
	public ResponseEntity<MovieMgmtSeatScheduleVOResponseObject> addSeatsSchedule(@RequestBody SeatScheduleVO seatSchedule) {

		LOGGER.debug("Entering the " + getClass().getSimpleName() + " class & addSeatsSchedule() method to add Seats with Movie Schedule");
		ResponseEntity<MovieMgmtSeatScheduleVOResponseObject> responseObj = null;
		MovieMgmtSeatScheduleVOResponseObject seatScheduleResponse = seatSchService.addSeatScheduleEntity(seatSchedule);
		responseObj = new ResponseEntity<MovieMgmtSeatScheduleVOResponseObject>(seatScheduleResponse, HttpStatus.CREATED);
		LOGGER.debug("Exiting the " + getClass().getSimpleName() + " class & addSeatsSchedule() method to add Seats with Movie Schedule");
		return responseObj;
	}
	

}
