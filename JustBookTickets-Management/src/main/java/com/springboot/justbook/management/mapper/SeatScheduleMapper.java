/**
 * 
 */
package com.springboot.justbook.management.mapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.justbook.management.domain.SeatSchedule;
import com.springboot.justbook.management.repository.MovieScheduleRepository;
import com.springboot.justbook.vo.SeatScheduleVO;

/**
 * @author M1006601
 *
 */
@Component
public class SeatScheduleMapper {
	
	/**
	 * LOGGER Logger object for logging in different logger Levels.
	 */
	static Logger LOGGER = LoggerFactory.getLogger(SeatScheduleMapper.class);
	
	@Autowired
	MovieScheduleRepository movieSchdRepository;

	public List<SeatScheduleVO> mapSeatSchedulesToVOList(List<SeatSchedule> seatSchdList) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & mapSeatSchedulesToVOList() method");
		List<SeatScheduleVO> seatScheduleList = new ArrayList<SeatScheduleVO>();
		for(SeatSchedule seatOccupied: seatSchdList) {
			seatScheduleList.add(mapSeatScheduleToVO(seatOccupied));
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & mapSeatSchedulesToVOList() method");
		return seatScheduleList;
	}

	public List<SeatScheduleVO> mapSeatScheduleToList(SeatSchedule seatSchd) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & mapSeatScheduleToVOList() method");
		List<SeatScheduleVO> seatScheduleList = new ArrayList<SeatScheduleVO>();
		seatScheduleList.add(mapSeatScheduleToVO(seatSchd));
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & mapSeatScheduleToVOList() method");
		return seatScheduleList;
	}
	private SeatScheduleVO mapSeatScheduleToVO(SeatSchedule seatSchd) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & mapSeatScheduleToVO() method");
		SeatScheduleVO seatSchdVO = new SeatScheduleVO();
		seatSchdVO.setId(seatSchd.getId());
		seatSchdVO.setScheduleId(seatSchd.getSchedule().getId());
		seatSchdVO.setSeatsOccupied(seatSchd.getSeatsOccupied());
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & mapSeatScheduleToVO() method");
		return seatSchdVO;
	}

	public SeatSchedule mapVOToScheduleEntity(SeatScheduleVO seatScheduleVO) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & mapVOToScheduleEntity() method");
		SeatSchedule seatSchd = new SeatSchedule();
		seatSchd.setId(seatScheduleVO.getId());
		seatSchd.setSchedule(movieSchdRepository.getOne(seatScheduleVO.getScheduleId()));
		seatSchd.setSeatsOccupied(seatScheduleVO.getSeatsOccupied());
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & mapVOToScheduleEntity() method");
		return seatSchd;
	}

}
