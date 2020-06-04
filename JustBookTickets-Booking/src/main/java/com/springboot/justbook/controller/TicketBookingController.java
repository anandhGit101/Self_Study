/**
 * 
 */
package com.springboot.justbook.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.justbook.clients.BookingClient;
import com.springboot.justbook.service.TicketBookingService;
import com.springboot.justbook.vo.BookingRequestVO;
import com.springboot.justbook.vo.BookingResponseObject;
import com.springboot.justbook.vo.BookingResponseVO;

import io.swagger.annotations.ApiOperation;

/**
 * @author M1006601
 *
 */
@RestController
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8899" }) 
public class TicketBookingController {
	
	/**
	 * LOGGER Logger object for logging in different logger Levels.
	 */
	static Logger LOGGER = LoggerFactory.getLogger(TicketBookingController.class);
	
	@Autowired
	TicketBookingService ticketBookingService;
	
	@GetMapping("/bookings/bookingnumber")
	@ApiOperation(value = "Get Booking Details by Booking Number", response=BookingResponseObject.class)
	public ResponseEntity<BookingResponseObject> getBookingDetailsByBookingNumber(@RequestParam String bookingNumber) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getBookingDetailsByBookingNumber() method to get BookingDetils");		
		ResponseEntity<BookingResponseObject> responseObj = null;
		BookingResponseObject bookingByBookingNumber = ticketBookingService.getBookingByBookingNumber(bookingNumber);
		switch (bookingByBookingNumber.getStatusCode()) {
		case HttpServletResponse.SC_FOUND:
			responseObj = new ResponseEntity<BookingResponseObject>(bookingByBookingNumber, HttpStatus.BAD_REQUEST);
			break;
		default:
			responseObj = new ResponseEntity<BookingResponseObject>(bookingByBookingNumber, HttpStatus.OK);
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getBookingDetailsByBookingNumber() method to fetch booking details");		
		return responseObj;
	}
	
	@GetMapping("/bookings/username")
	@ApiOperation(value = "Get Booking Details by User Name", response=BookingResponseObject.class)
	public ResponseEntity<BookingResponseObject> getBookingDetailsByUserName(@RequestParam String userName) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getBookingDetailsByUserName() method to get BookingDetils");		
		ResponseEntity<BookingResponseObject> responseObj = null;
		BookingResponseObject bookingByUserName = ticketBookingService.getBookingByBookingUser(userName);
		switch (bookingByUserName.getStatusCode()) {
		case HttpServletResponse.SC_FOUND:
			responseObj = new ResponseEntity<BookingResponseObject>(bookingByUserName, HttpStatus.BAD_REQUEST);
			break;
		default:
			responseObj = new ResponseEntity<BookingResponseObject>(bookingByUserName, HttpStatus.OK);
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getBookingDetailsByUserName() method to fetch booking details");		
		return responseObj;
	}
	
	@GetMapping("/bookings/phonenumber")
	@ApiOperation(value = "Get Booking Details by User Phone Number", response=BookingResponseObject.class)
	public ResponseEntity<BookingResponseObject> getBookingDetailsByUserPhoneNumber(@RequestParam String phoneNumber) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getBookingDetailsByUserPhoneNumber() method to get BookingDetils");		
		ResponseEntity<BookingResponseObject> responseObj = null;
		BookingResponseObject bookingByUserPhoneBookingNumber = ticketBookingService.getBookingByUserPhoneNumber(phoneNumber);
		switch (bookingByUserPhoneBookingNumber.getStatusCode()) {
		case HttpServletResponse.SC_FOUND:
			responseObj = new ResponseEntity<BookingResponseObject>(bookingByUserPhoneBookingNumber, HttpStatus.BAD_REQUEST);
			break;
		default:
			responseObj = new ResponseEntity<BookingResponseObject>(bookingByUserPhoneBookingNumber, HttpStatus.OK);
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getBookingDetailsByUserPhoneNumber() method to fetch booking details");		
		return responseObj;
	}
	
	@GetMapping("/bookings/moviename")
	@ApiOperation(value = "Get Booking Details by Movie Name", response=BookingResponseObject.class)
	public ResponseEntity<BookingResponseObject> getBookingDetailsByMovieName(@RequestParam String movieName) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getBookingDetailsByMovieName() method to get BookingDetils");		
		ResponseEntity<BookingResponseObject> responseObj = null;
		BookingResponseObject bookingByMovieName = ticketBookingService.getBookingByMovieName(movieName);
		switch (bookingByMovieName.getStatusCode()) {
		case HttpServletResponse.SC_FOUND:
			responseObj = new ResponseEntity<BookingResponseObject>(bookingByMovieName, HttpStatus.BAD_REQUEST);
			break;
		default:
			responseObj = new ResponseEntity<BookingResponseObject>(bookingByMovieName, HttpStatus.OK);
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getBookingDetailsByMovieName() method to fetch booking details");		
		return responseObj;
	}
	
	@PostMapping(path="/booktickets", consumes="application/json", produces="application/json")
	@ApiOperation(value = "Create booking for the Movie choosen", response=BookingResponseObject.class)
	public ResponseEntity<BookingResponseObject> bookTickets(@Valid @RequestBody BookingRequestVO requestVO, HttpServletResponse response) throws RestClientException, URISyntaxException{
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class &  bookTickets() method to book tickets.");
		ResponseEntity<BookingResponseObject> responseObj = null;
		BookingResponseObject createBookingResponse = ticketBookingService.createBooking(requestVO);
		switch (createBookingResponse.getStatusCode()) {
		case HttpServletResponse.SC_FOUND:
			responseObj = new ResponseEntity<BookingResponseObject>(createBookingResponse, HttpStatus.BAD_REQUEST);
			break;
		default:
			List<BookingResponseVO> list = createBookingResponse.getResultList();
			if(null!= list && !list.isEmpty()) {
			String number=list.get(0).getBookingNumber();
			URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/bookings/bookingnumber/{number}")
					.buildAndExpand(number).toUri();
			response.setHeader("Location", location.toString());
			responseObj = new ResponseEntity<BookingResponseObject>(createBookingResponse, HttpStatus.CREATED);
			}
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class &  bookTickets() method to book tickets.");
		return responseObj;
	}

}
