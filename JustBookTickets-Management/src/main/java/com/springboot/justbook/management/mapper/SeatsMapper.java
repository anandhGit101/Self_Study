/**
 * 
 */
package com.springboot.justbook.management.mapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.springboot.justbook.management.domain.Seats;
import com.springboot.justbook.vo.SeatsVO;

/**
 * @author M1006601
 *
 */
@Component
public class SeatsMapper {
	
	/**
	 * LOGGER Logger object for logging in different logger Levels.
	 */
	static Logger LOGGER = LoggerFactory.getLogger(SeatsMapper.class);

	public List<SeatsVO> mapSeatsToVOList(List<Seats> seatsList) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & mapSeatsToVOList() method");
		List<SeatsVO> seatsVOList = new ArrayList<SeatsVO>();
		for(Seats seat:seatsList) {
			seatsVOList.add(mapSeatsToSeatsVO(seat));
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & mapSeatsToVOList() method");
		return seatsVOList;
	}

	private SeatsVO mapSeatsToSeatsVO(Seats seat) {

		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & mapSeatsToSeatsVO() method");
		SeatsVO seatsVO = new SeatsVO();
		seatsVO.setSeatNumber(seat.getSeatNumber());
		seatsVO.setSeatType(seat.getSeatType());
		seatsVO.setId(seat.getId());
		seatsVO.setUnitPrice(seat.getUnitPrice());
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & mapSeatsToSeatsVO() method");
		return seatsVO;
	}

}
