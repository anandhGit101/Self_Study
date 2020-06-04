/**
 * 
 */
package com.springboot.justbook.service.impl;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.springboot.justbook.clients.BookingClient;
import com.springboot.justbook.config.Sender;
import com.springboot.justbook.domain.BookingDetails;
import com.springboot.justbook.mapper.TicketBookingMapper;
import com.springboot.justbook.repository.TicketBookingRepository;
import com.springboot.justbook.service.TicketBookingService;
import com.springboot.justbook.vo.BookingRequestVO;
import com.springboot.justbook.vo.BookingResponseObject;
import com.springboot.justbook.vo.BookingResponseVO;
import com.springboot.justbook.vo.CinemasVO;
import com.springboot.justbook.vo.MovieMgmtCinemasVOResponseObject;
import com.springboot.justbook.vo.MovieMgmtMovieScheduleVOResponseObject;
import com.springboot.justbook.vo.MovieMgmtMovieVOResponseObject;
import com.springboot.justbook.vo.MovieMgmtSeatScheduleVOResponseObject;
import com.springboot.justbook.vo.MovieScheduleRequestVO;
import com.springboot.justbook.vo.MovieVO;
import com.springboot.justbook.vo.SeatScheduleVO;

/**
 * @author M1006601
 *
 */
@Service
public class TicketBookingServiceImpl implements TicketBookingService {
	
	/**
	 * LOGGER Logger object for logging in different logger Levels.
	 */
	static Logger LOGGER = LoggerFactory.getLogger(TicketBookingServiceImpl.class);

	
	@Autowired
	BookingClient bookingClient;

	@Autowired
	TicketBookingRepository bookingRepository;
	
	@Autowired
	TicketBookingMapper bookingMapper;
	
	@Autowired
	Sender kafkaProducer;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Value("${moviemgmt.service.getmoviebyname}")
	private String getMovieURL;
	
	@Value("${moviemgmt.service.getcinemasbyname}")
	private String getCinemasURL;

	@Value("${moviemgmt.service.getschedule}")
	private String getScheduleURL;
	
	@Value("${seatmgmt.service.insertseatschedule}")
	private String insertSeatScheduleURL;
	
	@Value("${moviemgmt.service.putschedule}")
	private String putScheduleURL;
	
	
	@Override
	public BookingResponseObject createBooking(@Valid BookingRequestVO requestVO) throws RestClientException, URISyntaxException {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & createBooking() method to book Tickets");

		BookingResponseObject bookingResponse = new BookingResponseObject();
		BookingDetails bookingEntity = bookingMapper.mapTicketVOToTicketEntity(requestVO);
		
		BookingResponseObject existingBookingResponse = getBookingByBookingEntity(bookingEntity);
		BookingResponseVO bookingExisting = null;
		if(existingBookingResponse.getResultList()!=null) {
			bookingExisting=existingBookingResponse.getResultList().get(0);
		}
		 
		// Make call to MoveManagementService and get the movie id
		//MovieRequestVO movRequest = new MovieRequestVO(bookingEntity.getShowMovieName());
		LOGGER.debug("Fetching the movie information from "+getClass().getSimpleName()+" class & createBooking() method");
		//HttpEntity<String> httpMovieEntity = new HttpEntity<String>(bookingEntity.getShowMovieName());
		ResponseEntity<MovieMgmtMovieVOResponseObject> responseEntity = bookingClient.getMovieByName(bookingEntity.getShowMovieName()); 
				//restTemplate().exchange(getMovieURL, HttpMethod.GET, httpMovieEntity, MovieMgmtMovieVOResponseObject.class, bookingEntity.getShowMovieName());
		MovieVO movieResponse = (MovieVO)responseEntity.getBody().getResultList().get(0);
		
		LOGGER.debug("Fetched the movie information from "+getClass().getSimpleName()+" class & createBooking() method");
		// Make call to MoveManagementService and get the cinemas id
		//CinemasRequestVO cineRequest = new CinemasRequestVO(bookingEntity.getShowCinemasName());
		//HttpEntity<CinemasRequestVO> httpCinemasEntity = new HttpEntity<CinemasRequestVO>(cineRequest);
		LOGGER.debug("Fetching the cinemas information from "+getClass().getSimpleName()+" class & createBooking() method");
		ResponseEntity<MovieMgmtCinemasVOResponseObject> cinemasEntity = bookingClient.chooseCinemasByCinemasName(bookingEntity.getShowCinemasName());
				//restTemplate().exchange(getCinemasURL, HttpMethod.GET,httpCinemasEntity, MovieMgmtCinemasVOResponseObject.class, bookingEntity.getShowCinemasName());
		CinemasVO cinemasResponse = cinemasEntity.getBody().getResultList().get(0);
		LOGGER.debug("Fetched the cinemas information from "+getClass().getSimpleName()+" class & createBooking() method");
		// Make call to MovieManagementService with movieid, cinemasid, timings,date & fetch schedule id.
		MovieScheduleRequestVO schedVO = new MovieScheduleRequestVO();
		LOGGER.debug("Forming the schedule information from "+getClass().getSimpleName()+" class & createBooking() method");
		schedVO.setMovieId(movieResponse.getMovieId());
		schedVO.setCinemasId(cinemasResponse.getCinemasId());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		schedVO.setShowDate(LocalDate.parse(requestVO.getShowDate(), formatter));
		schedVO.setShowTimings(LocalTime.parse(requestVO.getShowTimings()));
		//HttpEntity<BookingMovieScheduleVO> httpEntity = new HttpEntity<>(schedVO);
		LOGGER.debug("Fetching the schedule information from "+getClass().getSimpleName()+" class & createBooking() method");
		//ResponseEntity<Long> postForEntity = restTemplate().exchange(getScheduleURL, HttpMethod.POST, httpEntity, long.class);
		
		long scheduleId = bookingClient.getSchdIdByMovieScheduleDetails(schedVO); //postForEntity.getBody().longValue();
		LOGGER.debug("Fetched the schedule information from "+getClass().getSimpleName()+" class & createBooking() method");
		// Make call to SeatScheduleService to insert seats occupied details
		SeatScheduleVO seatSchedVO = new SeatScheduleVO();
		seatSchedVO.setScheduleId(scheduleId); 
		seatSchedVO.setSeatsOccupied(requestVO.getSeatsSelected());
		//HttpEntity<BookingSeatScheduleVO> httpSeatScheduleEntity = new HttpEntity<>(seatSchedVO);
		LOGGER.debug("Insertion of seats information from "+getClass().getSimpleName()+" class & createBooking() method");
		ResponseEntity<MovieMgmtSeatScheduleVOResponseObject> postForSeatSchedule = bookingClient.addSeatsSchedule(seatSchedVO); 
				//restTemplate().exchange(insertSeatScheduleURL, HttpMethod.POST, httpSeatScheduleEntity, MovieMgmtSeatScheduleVOResponseObject.class);
		String seatsSelected = postForSeatSchedule.getBody().getResultList().get(0).getSeatsOccupied();
		// Make call to save the ticket details in BookingDetails entity.
		bookingEntity.setSeatsSelected(requestVO.getSeatsSelected());
		if(bookingExisting!=null) {
			bookingEntity.setSeatsAvailable(bookingExisting.getSeatsAvailable()==null? 60 : bookingExisting.getSeatsAvailable());
			bookingEntity.setSeatCategory(bookingExisting.getSeatCategory());
		}else {
			bookingEntity.setSeatsAvailable(60);
			bookingEntity.setSeatCategory(requestVO.getSeatCategory());
		}
		
		String seats[] = seatsSelected.split(",");
		String currentSeats[] = requestVO.getSeatsSelected().split(",");
		int currentNoOfSeatsAvailable = calculateSeatsAvailble(bookingEntity.getSeatsAvailable(), currentSeats.length);
		bookingEntity.setSeatsAvailable(currentNoOfSeatsAvailable);
		
		bookingEntity.setTotalCost(calculateTotalCost(requestVO.getSeatCategory(), new BigDecimal(currentSeats.length)));
		bookingEntity.setUserEmail(requestVO.getUserEmail());
		LOGGER.debug("Insertion of the booking information from "+getClass().getSimpleName()+" class & createBooking() method");
		Map<String, Object> uriMap = new HashMap<String, Object>();
		uriMap.put("SeatsAvailable", currentNoOfSeatsAvailable);
		uriMap.put("MovieId", Long.toString(movieResponse.getMovieId()));
		uriMap.put("CinemasId", Long.toString(cinemasResponse.getCinemasId()));
		uriMap.put("ShowDate", requestVO.getShowDate());
		uriMap.put("ShowTimings", requestVO.getShowTimings());
		HttpEntity<Map<String, Object>> httpSchedEntity = new HttpEntity<>(uriMap);
		ResponseEntity<MovieMgmtMovieScheduleVOResponseObject> putScheduleExchange = bookingClient.updateMovieSchedule(uriMap); 
				//restTemplate().exchange(putScheduleURL, HttpMethod.PUT, httpSchedEntity, MovieMgmtMovieScheduleVOResponseObject.class);
		bookingRepository.save(bookingEntity);
		kafkaProducer.send(bookingEntity);
		List<BookingResponseVO> responseVOList = bookingMapper.mapTicketToTicketVOList(bookingEntity);
		bookingResponse.setResultList(responseVOList);
		LOGGER.debug("Insertion of the booking information completed from "+getClass().getSimpleName()+" class & createBooking() method");
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & createBooking() method to book Tickets");
		return bookingResponse;
	}


	private BigDecimal calculateTotalCost(String seatCategory, BigDecimal seatsSelected) {
		
		LOGGER.debug("Inside method to Calculate Total Cost from "+getClass().getSimpleName()+" class");
		BigDecimal totalPrice = null; 
		if(seatCategory.equalsIgnoreCase("FIRST_CLASS")) {
			totalPrice = new BigDecimal(183.65).multiply(seatsSelected);
		} else if(seatCategory.equalsIgnoreCase("BUDGET_CLASS")) {
			totalPrice = new BigDecimal(75.65).multiply(seatsSelected);
		}
		return totalPrice;
	}


	private int calculateSeatsAvailble(int seatsAvailable, int length) {
		
		LOGGER.debug("Inside method to calculate seats available from "+getClass().getSimpleName()+" class");
		return seatsAvailable-length;
	}


	@Override
	public BookingResponseObject getBookingByBookingNumber(String bookingNumber) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getBookingByBookingNumber() method to fetch Booking information by booking number");
		BookingResponseObject bookingObject = new BookingResponseObject();
		BookingDetails findByBookingNumber = bookingRepository.findByBookingNumber(bookingNumber);
		if(null!=findByBookingNumber) {
			bookingObject.setResultList(bookingMapper.mapTicketToTicketVOList(findByBookingNumber));
		} else {
			bookingObject.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
			bookingObject.setErrorCode("notFound");
			bookingObject.setDescription("Booking with "+bookingNumber+" is not a valid booking");
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getBookingByBookingNumber() method to fetch Booking information by booking number");		
		return bookingObject;
		
	}


	@Override
	public BookingResponseObject getBookingByBookingUser(String userName) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getBookingByBookingUser() method to fetch Booking information by user name");
		BookingResponseObject bookingObject = new BookingResponseObject();
		List<BookingDetails> findByUser = bookingRepository.findByUserName(userName);
		if(null!=findByUser) {
			bookingObject.setResultList(bookingMapper.mapTicketListToTicketVOList(findByUser));
		} else {
			bookingObject.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
			bookingObject.setErrorCode("notFound");
			bookingObject.setDescription("Booking with "+userName+" does not have a valid booking");
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getBookingByBookingUser() method to fetch Booking information by user name");		
		return bookingObject;
	}


	@Override
	public BookingResponseObject getBookingByUserPhoneNumber(String phoneNumber) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getBookingByUserPhoneNumber() method to fetch Booking information by phone number");
		BookingResponseObject bookingObject = new BookingResponseObject();
		List<BookingDetails> findByPhoneNumber = bookingRepository.findByUserPhoneNumber(phoneNumber);
		if(null!=findByPhoneNumber) {
			bookingObject.setResultList(bookingMapper.mapTicketListToTicketVOList(findByPhoneNumber));
		} else {
			bookingObject.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
			bookingObject.setErrorCode("notFound");
			bookingObject.setDescription("Booking with "+phoneNumber+" does not have a valid booking");
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getBookingByUserPhoneNumber() method to fetch Booking information by phone number");		
		return bookingObject;
	}


	@Override
	public BookingResponseObject getBookingByBookingEntity(BookingDetails bookingEntity) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getBookingByBookingEntity() method");
		BookingResponseObject bookingObject = new BookingResponseObject();
		List<BookingDetails> findByMovieName = bookingRepository.findByShowMovieName(bookingEntity.getShowMovieName());
		if(null!=findByMovieName) {
			List<BookingResponseVO> bookingResponseVOList = bookingMapper.mapTicketListToTicketVOList(findByMovieName);
			bookingObject.setResultList(bookingResponseVOList);
		} else {
			bookingObject.setResultList(bookingMapper.mapEntityToVoList(bookingEntity));
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getBookingByBookingEntity() method");		
		return bookingObject;
	}


	@Override
	public BookingResponseObject getBookingByMovieName(String movieName) {
		
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getBookingByMovieName() method to fetch Booking information by movie name");
		BookingResponseObject bookingObject = new BookingResponseObject();
		List<BookingDetails> findByMovieName = bookingRepository.findByShowMovieName(movieName);
		if(null!=findByMovieName) {
			bookingObject.setResultList(bookingMapper.mapTicketListToTicketVOList(findByMovieName));
		}else {
			bookingObject.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
			bookingObject.setErrorCode("notFound");
			bookingObject.setDescription("Booking with "+movieName+" does not have a valid booking");
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getBookingByMovieName() method to fetch Booking information by movie name");
		return bookingObject;
	}
		
}
