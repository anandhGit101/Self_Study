/**
 * 
 */
package com.springboot.justbook.management.service;

import java.util.Map;

import com.springboot.justbook.management.exception.MovieMgmtException;
import com.springboot.justbook.vo.MovieMgmtMovieScheduleVOResponseObject;
import com.springboot.justbook.vo.MovieScheduleRequestVO;

/**
 * @author M1006601
 *
 */
public interface MovieScheduleService {
	
	MovieMgmtMovieScheduleVOResponseObject getScheduleByCinemasId(Long cinemasId) throws MovieMgmtException;

	MovieMgmtMovieScheduleVOResponseObject getScheduleByMovieName(String movieName);

	MovieMgmtMovieScheduleVOResponseObject getSchedulesByIds(Long cinemasId, Long movieId);

	MovieMgmtMovieScheduleVOResponseObject getSchedulesByCinemasName(String cinemasName) throws MovieMgmtException;

	MovieMgmtMovieScheduleVOResponseObject getSchedules(String cinemasName, String movieName);

	void deleteScheduleById(Long cinemasId);

	long getSchdIdByMovieScheduleDetails(MovieScheduleRequestVO requestVO);

	MovieMgmtMovieScheduleVOResponseObject getScheduleByMovieId(Long movieId);

	MovieMgmtMovieScheduleVOResponseObject saveAndUpdate(Map<String, Object> updateScheduleMap);
	

}
