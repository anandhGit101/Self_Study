/**
 * 
 */
package com.springboot.justbook.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.justbook.management.domain.SeatSchedule;

/**
 * @author M1006601
 *
 */
@Repository
public interface SeatScheduleRepository extends JpaRepository<SeatSchedule, Long>{

	@Query(nativeQuery = true, value = "SELECT * FROM seat_schedule left join movie_schedule on seat_schedule.schedule_movie_schedule_id = movie_schedule.movie_schedule_id where seat_schedule.schedule_movie_schedule_id=:movieScheduleId") 
	SeatSchedule findSeatScheduleByMovieScheduleId(Long movieScheduleId);
	
	SeatSchedule findSeatScheduleByScheduleId(Long seatScheduleId);
	
}
