/**
 * 
 */
package com.springboot.justbook.management.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.justbook.management.domain.SeatSchedule;
import com.springboot.justbook.management.mapper.SeatScheduleMapper;
import com.springboot.justbook.management.repository.MovieScheduleRepository;
import com.springboot.justbook.management.repository.SeatScheduleRepository;
import com.springboot.justbook.management.service.SeatScheduleService;
import com.springboot.justbook.vo.MovieMgmtSeatScheduleVOResponseObject;
import com.springboot.justbook.vo.SeatScheduleVO;

/**
 * @author M1006601
 *
 */
@Service
public class SeatScheduleServiceImpl implements SeatScheduleService {

	static Logger LOGGER = LoggerFactory.getLogger(SeatScheduleServiceImpl.class);
	
	@Autowired
	SeatScheduleRepository seatScheduleRepository;
	
	@Autowired
	MovieScheduleRepository movieSchdRepository;
	
	@Autowired
	SeatScheduleMapper scheduleMapper;	
	
	@Override
	public MovieMgmtSeatScheduleVOResponseObject findSeatScheduleByMovieScheduleId(Long scheduleId) {

		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & findSeatScheduleByMovieScheduleId() method to fetch list of scheduled seats");
		MovieMgmtSeatScheduleVOResponseObject response = new MovieMgmtSeatScheduleVOResponseObject();
		SeatSchedule seatSchd=seatScheduleRepository.findSeatScheduleByMovieScheduleId(scheduleId);
		if(null!=seatSchd) {
			response.setResultList(scheduleMapper.mapSeatScheduleToList(seatSchd));
		}else {
			SeatScheduleVO seatScheduleVO = new SeatScheduleVO();
			List<SeatScheduleVO> resultList=new ArrayList<SeatScheduleVO>();
			resultList.add(seatScheduleVO);
			response.setResultList(resultList);
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & findSeatScheduleByMovieScheduleId() method to fetch list of scheduled seats");
		return response;
	}

	@Override
	public MovieMgmtSeatScheduleVOResponseObject addSeatScheduleEntity(SeatScheduleVO seatSchedule) {

		LOGGER.debug("Entering the " + getClass().getSimpleName() + " class & addSeatScheduleEntity() method to add Seats to Movie Schedule");
		MovieMgmtSeatScheduleVOResponseObject response = new MovieMgmtSeatScheduleVOResponseObject();
		SeatSchedule seatSchdExisting = seatScheduleRepository
				.findSeatScheduleByMovieScheduleId(seatSchedule.getScheduleId());
		if (null == seatSchdExisting) {
			SeatSchedule seatScheduleEntity = scheduleMapper.mapVOToScheduleEntity(seatSchedule);
			seatScheduleRepository.save(seatScheduleEntity);
			List<SeatScheduleVO> mapSeatScheduleToVOList = scheduleMapper.mapSeatScheduleToList(seatScheduleEntity);
			response.setResultList(mapSeatScheduleToVOList);
			response.setId(seatScheduleEntity.getId());
		} else {

			String seatsOccupiedExisting = seatSchdExisting.getSeatsOccupied();
			seatSchedule.setId(seatSchdExisting.getId());
			seatSchedule.setSeatsOccupied(seatsOccupiedExisting.concat(",").concat(seatSchedule.getSeatsOccupied()));
			SeatSchedule seatScheduleEntity = scheduleMapper.mapVOToScheduleEntity(seatSchedule);
			seatScheduleRepository.save(seatScheduleEntity);
			List<SeatScheduleVO> mapSeatScheduleToVOList = scheduleMapper.mapSeatScheduleToList(seatScheduleEntity);
			response.setResultList(mapSeatScheduleToVOList);
			response.setId(seatScheduleEntity.getId());
		}
		LOGGER.debug("Exiting the " + getClass().getSimpleName() + " class & addSeatScheduleEntity() method to add Seats to Movie Schedule");
		return response;
	}

}