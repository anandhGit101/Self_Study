/**
 * 
 */
package com.springboot.justbook.clients;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.justbook.vo.MovieMgmtCinemasVOResponseObject;
import com.springboot.justbook.vo.MovieMgmtMovieScheduleVOResponseObject;
import com.springboot.justbook.vo.MovieMgmtMovieVOResponseObject;
import com.springboot.justbook.vo.MovieMgmtSeatScheduleVOResponseObject;
import com.springboot.justbook.vo.MovieScheduleRequestVO;
import com.springboot.justbook.vo.SeatScheduleVO;

/**
 * @author M1006601
 *
 */
@FeignClient(value="moviemanagement", url="http://localhost:8007/jbtMovieMgmt")
public interface BookingClient {

	@GetMapping(path="/movie/listbyname", produces = "application/json")
	public ResponseEntity<MovieMgmtMovieVOResponseObject> getMovieByName(@RequestParam String movieName);
	
	@GetMapping(path="/cinemas/list", produces = "application/json")
	public ResponseEntity<MovieMgmtCinemasVOResponseObject> chooseCinemasByCinemasName(@RequestParam String cinemasName);
	
	@PostMapping(path="/schedules/showdetails", consumes="application/json")
	public long getSchdIdByMovieScheduleDetails(@RequestBody MovieScheduleRequestVO requestVO);
	
	@GetMapping(path = "/seatsschedule/booked", produces = "application/json")
	public ResponseEntity<MovieMgmtSeatScheduleVOResponseObject> addSeatsSchedule(@RequestBody SeatScheduleVO seatSchedule);
	
	@PutMapping(path="/schedules/showdetails", consumes="application/json")
	public ResponseEntity<MovieMgmtMovieScheduleVOResponseObject> updateMovieSchedule(@RequestBody Map<String, Object> updateFields);
}
