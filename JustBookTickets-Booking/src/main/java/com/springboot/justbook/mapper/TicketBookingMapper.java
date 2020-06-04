/**
 * 
 */
package com.springboot.justbook.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.springboot.justbook.domain.BookingDetails;
import com.springboot.justbook.vo.BookingRequestVO;
import com.springboot.justbook.vo.BookingResponseVO;

/**
 * @author M1006601
 *
 */
@Component
public class TicketBookingMapper {
	
	/**
	 * LOGGER Logger object for logging in different logger Levels.
	 */
	static Logger LOGGER = LoggerFactory.getLogger(TicketBookingMapper.class);	

	public BookingDetails mapTicketVOToTicketEntity(@Valid BookingRequestVO requestVO) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & mapTicketVOToTicketEntity() method");
		BookingDetails bookingDetails = new BookingDetails();
		bookingDetails.setBookingNumber(generateUniqueNumber(8));
		bookingDetails.setShowMovieName(requestVO.getShowMovieName());
		bookingDetails.setShowCinemasName(requestVO.getShowCinemasName());
		bookingDetails.setShowDate(requestVO.getShowDate());
		bookingDetails.setShowTimings(requestVO.getShowTimings());
		bookingDetails.setSeatsSelected(requestVO.getSeatsSelected());
		bookingDetails.setUserName(requestVO.getUserName());
		bookingDetails.setUserEmail(requestVO.getUserEmail());
		bookingDetails.setUserPhoneNumber(requestVO.getUserPhoneNumber());
		bookingDetails.setSeatCategory(requestVO.getSeatCategory());
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & mapTicketVOToTicketEntity() method");
		return bookingDetails;
	}

	private String generateUniqueNumber(int n) {
	
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}

	public List<BookingResponseVO> mapTicketToTicketVOList(BookingDetails bookingEntity){
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & mapTicketToTicketVOList() method");
		List<BookingResponseVO> bookingVOList = new ArrayList<BookingResponseVO>();
		bookingVOList.add(mapTicketToTicketVO(bookingEntity));
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & mapTicketToTicketVOList() method");
		return bookingVOList;	
	}
	
	
	public BookingResponseVO mapTicketToTicketVO(BookingDetails bookingEntity) {

		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & mapTicketToTicketVO() method");
		BookingResponseVO bookingResponse = new BookingResponseVO();
		bookingResponse.setBookingNumber(bookingEntity.getBookingNumber());
		bookingResponse.setShowMovieName(bookingEntity.getShowMovieName());
		bookingResponse.setShowCinemasName(bookingEntity.getShowCinemasName());
		bookingResponse.setShowDate(bookingEntity.getShowDate());
		bookingResponse.setShowTimings(bookingEntity.getShowTimings());
		bookingResponse.setSeatsSelected(bookingEntity.getSeatsSelected());
		bookingResponse.setUserName(bookingEntity.getUserName());
		bookingResponse.setUserEmail(bookingEntity.getUserEmail());
		bookingResponse.setUserPhoneNumber(bookingEntity.getUserPhoneNumber());
		bookingResponse.setSeatsAvailable(bookingEntity.getSeatsAvailable());
		bookingResponse.setSeatCategory(bookingEntity.getSeatCategory());
		bookingResponse.setTotalPrice(bookingEntity.getTotalCost());
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & mapTicketToTicketVO() method");
		return bookingResponse;
	}

	public List<BookingResponseVO> mapEntityToVoList(BookingDetails bookingEntity) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & mapEntityToVoList() method");
		List<BookingResponseVO> bookingVOList = new ArrayList<BookingResponseVO>();
		BookingResponseVO responseVO = new BookingResponseVO();
		responseVO.setBookingNumber(bookingEntity.getBookingNumber());
		responseVO.setShowMovieName(bookingEntity.getShowMovieName());
		responseVO.setShowTimings(bookingEntity.getShowTimings());
		responseVO.setSeatsSelected(bookingEntity.getSeatsSelected());
		responseVO.setUserName(bookingEntity.getUserName());
		responseVO.setUserEmail(bookingEntity.getUserEmail());
		responseVO.setShowDate(bookingEntity.getShowDate());
		responseVO.setUserPhoneNumber(bookingEntity.getUserPhoneNumber());
		responseVO.setSeatsAvailable(bookingEntity.getSeatsAvailable());
		responseVO.setSeatCategory(bookingEntity.getSeatCategory());
		responseVO.setTotalPrice(bookingEntity.getTotalCost());
		bookingVOList.add(responseVO);
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & mapEntityToVoList() method");
		return bookingVOList;
	}

	public List<BookingResponseVO> mapTicketListToTicketVOList(List<BookingDetails> findByMovieName) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & mapTicketListToTicketVOList() method");
		List<BookingResponseVO> bookingVOList = new ArrayList<BookingResponseVO>();
		BookingResponseVO bookingResponseVO = null;
		for(BookingDetails details : findByMovieName) {
			if(null == bookingResponseVO) {
				bookingResponseVO = mapTicketToTicketVO(details);
			} else {
				bookingResponseVO.setSeatsSelected(bookingResponseVO.getSeatsSelected().concat(details.getSeatsSelected()));
			}
				bookingVOList.add(bookingResponseVO);			
		}
		return bookingVOList;
	}
}