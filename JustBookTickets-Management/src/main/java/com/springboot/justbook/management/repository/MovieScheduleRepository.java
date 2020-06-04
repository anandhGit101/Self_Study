/**
 * 
 */
package com.springboot.justbook.management.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.justbook.management.domain.MovieSchedule;

/**
 * @author M1006601
 *
 */
@Repository
@Transactional 
public interface MovieScheduleRepository extends JpaRepository<MovieSchedule, Long> {

	@Query(nativeQuery = true, value = "select * from movie_schedule where cinemas_Id=:cinemaId")
	List<MovieSchedule> getMovieScheduleByCinemasId(Long cinemaId);

	@Query(nativeQuery = true, value = "select * from movie_schedule where movie_Id=:movieId")
	List<MovieSchedule> getMovieScheduleByMovieId(Long movieId);

	@Query(nativeQuery = true, value = "select * from movie_schedule where movie_Id=:movieId and cinemas_Id=:cinemaId")
	List<MovieSchedule> getMovieScheduleByMovieIdAndCinemasId(Long movieId, Long cinemaId);

	@Query(nativeQuery = true, value = "select * from movie_schedule where cinemas_Id=:cinemasId and movie_Id=:movieId and show_date=:showDate and show_timings=:showTime")
	MovieSchedule findScheduleIdByIdsAndTimings(Long cinemasId, Long movieId, LocalDate showDate, LocalTime showTime);

	@Query(nativeQuery = true, value = "select * from movie_schedule where cinemas_Id=:cinemasId and movie_Id=:movieId and show_date=:showDate and show_timings=:showTime")
	MovieSchedule getMovieScheduleByIdsAndShowDetails(String movieId, String cinemasId, LocalDate showDate, LocalTime showTime);
	
	@Modifying
	@Query(nativeQuery = true, value = "update movie_schedule set tickets_available=:seatsAvailable where cinemas_Id=:cinemasId and movie_Id=:movieId and show_date=:showDate and show_timings=:showTime")
	void updateMovieSchedule(int seatsAvailable, String movieId, String cinemasId, LocalDate showDate, LocalTime showTime);

}
