/**
 * 
 */
package com.springboot.justbook.service;

import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.web.client.RestClientException;

import com.springboot.justbook.domain.BookingDetails;
import com.springboot.justbook.vo.BookingRequestVO;
import com.springboot.justbook.vo.BookingResponseObject;

/**
 * @author M1006601
 *
 */
public interface TicketBookingService {

	BookingResponseObject createBooking(@Valid BookingRequestVO requestVO) throws RestClientException, URISyntaxException;

	BookingResponseObject getBookingByBookingNumber(String bookingNumber);

	BookingResponseObject getBookingByBookingUser(String userName);

	BookingResponseObject getBookingByUserPhoneNumber(String phoneNumber);

	BookingResponseObject getBookingByBookingEntity(BookingDetails bookingEntity);

	BookingResponseObject getBookingByMovieName(String movieName);

}
