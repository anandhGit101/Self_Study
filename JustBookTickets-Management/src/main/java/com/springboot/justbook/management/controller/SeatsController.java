/**
 * 
 */
package com.springboot.justbook.management.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.justbook.management.exception.SeatsMgmtException;
import com.springboot.justbook.management.service.SeatsService;
import com.springboot.justbook.vo.MovieMgmtSeatsVOResponseObject;

import io.swagger.annotations.ApiOperation;

/**
 * @author M1006601
 *
 */
@RestController
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8899" }) 
public class SeatsController {
	
	/**
	 * LOGGER Logger object for logging in different logger Levels.
	 */
	static Logger LOGGER = LoggerFactory.getLogger(SeatsController.class);
	
	@Autowired
	SeatsService seatsService;

	@GetMapping(path="/seats/list", produces = "application/json")
	@ApiOperation(value = "Get Available List of Seats with details", response=MovieMgmtSeatsVOResponseObject.class)
	ResponseEntity<MovieMgmtSeatsVOResponseObject> getSeats() throws SeatsMgmtException{
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getSeats() method to fetch list of Movies");		
		ResponseEntity<MovieMgmtSeatsVOResponseObject> responseObj = null;
		MovieMgmtSeatsVOResponseObject response = seatsService.getSeats();
		switch (response.getStatusCode()) {
		case HttpServletResponse.SC_NOT_FOUND:
			responseObj = new ResponseEntity<MovieMgmtSeatsVOResponseObject>(response, HttpStatus.NOT_FOUND);
			break;
		default:
			responseObj = new ResponseEntity<MovieMgmtSeatsVOResponseObject>(response, HttpStatus.OK);
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getSeats() method to fetch list of Movies");
		return responseObj;
	}

}
