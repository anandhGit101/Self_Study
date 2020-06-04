/**
 * 
 */
package com.springboot.justbook.management.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.justbook.management.domain.Cinemas;
import com.springboot.justbook.management.domain.Movie;
import com.springboot.justbook.management.domain.MovieSchedule;
import com.springboot.justbook.management.exception.MovieMgmtException;
import com.springboot.justbook.management.mapper.MovieMgmtMapper;
import com.springboot.justbook.management.repository.CinemasRepository;
import com.springboot.justbook.management.repository.MovieRepository;
import com.springboot.justbook.management.repository.MovieScheduleRepository;
import com.springboot.justbook.management.service.MovieScheduleService;
import com.springboot.justbook.vo.MovieMgmtMovieScheduleVOResponseObject;
import com.springboot.justbook.vo.MovieScheduleRequestVO;
import com.springboot.justbook.vo.MovieScheduleVO;

/**
 * @author M1006601
 *
 */
@Service
public class MovieScheduleServiceImpl implements MovieScheduleService {
	
	/**
	 * LOGGER Logger object for logging in different logger Levels.
	 */
	static Logger LOGGER = LoggerFactory.getLogger(MovieScheduleServiceImpl.class);
	
	@Autowired
	MovieScheduleRepository scheduleRepository;
	
	@Autowired
	MovieRepository movieRespository;
	
	@Autowired
	CinemasRepository cinemasRepository;
	
	@Autowired
	MovieMgmtMapper mapper;
	
	@Override
	public MovieMgmtMovieScheduleVOResponseObject getScheduleByCinemasId(Long cinemasId) throws MovieMgmtException {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getScheduleByCinemasId() method with cinemasId: "+cinemasId);
		MovieMgmtMovieScheduleVOResponseObject response = new MovieMgmtMovieScheduleVOResponseObject();
		Cinemas cinemas = cinemasRepository.findCinemasByCinemasId(cinemasId);
		if(null !=cinemas) {
			Map<String, MovieScheduleVO> movSchdVoList=new HashMap<String, MovieScheduleVO>();
			movSchdVoList=mapper.mapSchedulesListToSchedulesVOMap(cinemas.getMovieSchedule());
			response.setResultList(movSchdVoList);			
		} else {
			throw new MovieMgmtException("list is empty"); 
			/*response.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
			response.setErrorCode("notFound");
			response.setDescription("No MovieSchedule found for Cinemas with the id:"+cinemasId);*/
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getScheduleByCinemasId() method with cinemasId: "+cinemasId);
		return response;
	}

	@Override
	public MovieMgmtMovieScheduleVOResponseObject getScheduleByMovieName(String movieName) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getScheduleByMovieName() method with movieName: "+movieName);
		MovieMgmtMovieScheduleVOResponseObject response = new MovieMgmtMovieScheduleVOResponseObject();
		Movie movie=movieRespository.findMovieByMovieTitleAndIsMovieActiveTrue(movieName);
		if(null!= movie) {
			Map<String, MovieScheduleVO> movSchdVoMap=new HashMap<String, MovieScheduleVO>();
			movSchdVoMap=mapper.mapSchedulesListToSchedulesVOMap(movie.getMovieSchedule());
			response.setResultList(movSchdVoMap);			 
		} else {
			response.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
			response.setErrorCode("notFound");
			response.setDescription("No Schedule found for Movie with the name:"+movieName);
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getScheduleByMovieName() method with movieName: "+movieName);
		return response;
	}

	@Override
	public MovieMgmtMovieScheduleVOResponseObject getSchedules(String movieName, String cinemasName) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getSchedules() method with movieName: "+movieName+" and cinemasName: "+cinemasName);
		MovieMgmtMovieScheduleVOResponseObject response = new MovieMgmtMovieScheduleVOResponseObject();
		Cinemas cinemas = cinemasRepository.findCinemasByCinemasName(cinemasName);
		Movie movie=movieRespository.findMovieByMovieTitleAndIsMovieActiveTrue(movieName);
		if(null!= cinemas && null!=movie) {
			List<MovieSchedule> schedulesList = scheduleRepository.getMovieScheduleByMovieIdAndCinemasId(movie.getMovieId(), cinemas.getCinemasId());
			Map<String, MovieScheduleVO> movSchdVoList=null;
			if(null !=schedulesList) {
				movSchdVoList=mapper.mapSchedulesListToSchedulesVOList(schedulesList);
				response.setResultList(movSchdVoList);			
			} 
		}else {
			response.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
			response.setErrorCode("notFound");
			response.setDescription("No Schedule found for Movie and Cinemas");
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getSchedules() method with movieName: "+movieName+" and cinemasName: "+cinemasName);
		return response;
	}

	@Override
	public MovieMgmtMovieScheduleVOResponseObject getSchedulesByIds(Long movieId, Long cinemasId) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+"class"+getClass().getEnclosingMethod());
		MovieMgmtMovieScheduleVOResponseObject response = new MovieMgmtMovieScheduleVOResponseObject();
		List<MovieSchedule> schedulesList = scheduleRepository.getMovieScheduleByMovieIdAndCinemasId(movieId, cinemasId);
		Map<String, MovieScheduleVO> movSchdVoList=new HashMap<String, MovieScheduleVO>();
		if(null !=schedulesList) {
			movSchdVoList=mapper.mapSchedulesListToSchedulesVOList(schedulesList);
			response.setResultList(movSchdVoList);			
		} else {
			response.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
			response.setErrorCode("notFound");
			response.setDescription("No Schedule found for Movie and Cinemas");
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+"class");
		return response;
	}

	@Override
	public MovieMgmtMovieScheduleVOResponseObject getSchedulesByCinemasName(String cinemasName) throws MovieMgmtException{
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+"class & getSchedulesByCinemasName() method with CinemasName: "+cinemasName);
		MovieMgmtMovieScheduleVOResponseObject response = new MovieMgmtMovieScheduleVOResponseObject();
		Cinemas cinemas = cinemasRepository.findCinemasByCinemasName(cinemasName);
		if (null != cinemas) {
			Map<String, MovieScheduleVO> movSchdVoList = new HashMap<String, MovieScheduleVO>();
			movSchdVoList = mapper.mapSchedulesListToSchedulesVOList(cinemas.getMovieSchedule());
			response.setResultList(movSchdVoList);

		} else {
			String errorMessage = HttpServletResponse.SC_NOT_FOUND + "~" + "notFound " + "~"
					+ "No Schedule found for Movie with the Cinemas name:" + cinemasName;
			throw new MovieMgmtException(errorMessage);
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+"class & getSchedulesByCinemasName() method with CinemasName:"+cinemasName);
		return response;
	}

	@Override
	public void deleteScheduleById(Long cinemasId) {

		LOGGER.debug("Entering the "+getClass().getSimpleName()+"class deleteScheduleById() method");
		scheduleRepository.deleteById(cinemasId);
	}

	@Override
	public long getSchdIdByMovieScheduleDetails(MovieScheduleRequestVO requestVO) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class getSchdIdByMovieScheduleDetails()");
		MovieSchedule findScheduleIdByIdsAndTimings = scheduleRepository.findScheduleIdByIdsAndTimings(requestVO.getCinemasId(), requestVO.getMovieId(), requestVO.getShowDate(), requestVO.getShowTimings());
		long scheduleId = 0;
		if(null!=findScheduleIdByIdsAndTimings) {
			scheduleId=findScheduleIdByIdsAndTimings.getId();
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class getSchdIdByMovieScheduleDetails()");
		return scheduleId;
	}

	@Override
	public MovieMgmtMovieScheduleVOResponseObject getScheduleByMovieId(Long movieId) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getScheduleByMovieId() method");
		MovieMgmtMovieScheduleVOResponseObject response = new MovieMgmtMovieScheduleVOResponseObject();
		
		List<MovieSchedule> schedulesList = scheduleRepository.getMovieScheduleByMovieId(movieId);
		Map<String, MovieScheduleVO> movSchdVoList=new HashMap<String, MovieScheduleVO>();
		System.out.println("SchedulesList"+schedulesList.size());
		if(null !=schedulesList && !schedulesList.isEmpty()) {
			movSchdVoList=mapper.mapSchedulesListToSchedulesVOList(schedulesList);
			response.setResultList(movSchdVoList);			
		} else {
			response.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
			response.setErrorCode("notFound");
			response.setDescription("No MovieSchedule found for Movie with the id:"+movieId);
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getScheduleByMovieId() method");
		return response;
	}

	@Override
	public MovieMgmtMovieScheduleVOResponseObject saveAndUpdate(Map<String, Object> updateScheduleMap) {

		LOGGER.debug("Entering the " + getClass().getSimpleName() + " class & saveAndUpdate() method");
		
		String date = updateScheduleMap.get("ShowDate").toString();
		String time = updateScheduleMap.get("ShowTimings").toString();
		//convert String to LocalDate
		LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
		
		LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);
		
		MovieMgmtMovieScheduleVOResponseObject response = new MovieMgmtMovieScheduleVOResponseObject();
		MovieSchedule movieScheduleByIdsAndShowDetails = scheduleRepository.getMovieScheduleByIdsAndShowDetails(
				updateScheduleMap.get("MovieId").toString(), updateScheduleMap.get("CinemasId").toString(),
				localDate , localTime);
		Map<String, MovieScheduleVO> movSchdVoMap = null;
		if (null != movieScheduleByIdsAndShowDetails) {
			scheduleRepository.updateMovieSchedule(Integer.parseInt(updateScheduleMap.get("SeatsAvailable").toString()),
					updateScheduleMap.get("MovieId").toString(), updateScheduleMap.get("CinemasId").toString(),
					localDate, localTime);
			movSchdVoMap = mapper.mapScheduleToSchedulesVOList(movieScheduleByIdsAndShowDetails);
			response.setResultList(movSchdVoMap);
		} else {
			response.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
			response.setErrorCode("notFound");
			response.setDescription("No MovieSchedule found for Schedule with the show date: "+updateScheduleMap.get("ShowDate").toString()
					+"and timings: "+updateScheduleMap.get("ShowTimings").toString());
		}
		LOGGER.debug("Exiting the " + getClass().getSimpleName() + " class & saveAndUpdate() method");
		return response;
	}
	
	

	
}
