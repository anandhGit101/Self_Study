/**
 * 
 */
package com.springboot.justbook.management.service;

import com.springboot.justbook.vo.MovieMgmtSeatScheduleVOResponseObject;
import com.springboot.justbook.vo.SeatScheduleVO;

/**
 * @author M1006601
 *
 */
public interface SeatScheduleService {

	MovieMgmtSeatScheduleVOResponseObject findSeatScheduleByMovieScheduleId(Long scheduleId);

	MovieMgmtSeatScheduleVOResponseObject addSeatScheduleEntity(SeatScheduleVO seatSchedule);

}
