/**
 * 
 */
package com.springboot.justbook.management.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.justbook.management.exception.SeatsMgmtException;
import com.springboot.justbook.management.mapper.SeatsMapper;
import com.springboot.justbook.management.repository.SeatsRepository;
import com.springboot.justbook.management.service.SeatsService;
import com.springboot.justbook.vo.MovieMgmtSeatsVOResponseObject;

/**
 * @author M1006601
 *
 */
@Service
public class SeatsServiceImpl implements SeatsService {
	
	/**
	 * LOGGER Logger object for logging in different logger Levels.
	 */
	static Logger LOGGER = LoggerFactory.getLogger(SeatScheduleServiceImpl.class);
	
	@Autowired
	SeatsRepository seatsRepository;
	
	@Autowired
	SeatsMapper mapper;

	@Override
	public MovieMgmtSeatsVOResponseObject getSeats() throws SeatsMgmtException{
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getSeats() method to fetch list of Movies");
		MovieMgmtSeatsVOResponseObject response = new MovieMgmtSeatsVOResponseObject();
		response.setResultList(mapper.mapSeatsToVOList(seatsRepository.findAll()));
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getSeats() method to fetch list of Movies");
		return response;
	}

}
