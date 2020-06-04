/**
 * 
 */
package com.springboot.justbook.vo;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author M1006601
 *
 */
public class MovieScheduleRequestVO implements GenericVO{
	
	private Long cinemasId;
	
	private Long movieId;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate showDate;
	
	private LocalTime showTimings;

	/**
	 * @return the cinemasId
	 */
	public Long getCinemasId() {
		return cinemasId;
	}

	/**
	 * @param cinemasId the cinemasId to set
	 */
	public void setCinemasId(Long cinemasId) {
		this.cinemasId = cinemasId;
	}

	/**
	 * @return the movieId
	 */
	public Long getMovieId() {
		return movieId;
	}

	/**
	 * @param movieId the movieId to set
	 */
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	/**
	 * @return the showDate
	 */
	public LocalDate getShowDate() {
		return showDate;
	}

	/**
	 * @param showDate the showDate to set
	 */
	public void setShowDate(LocalDate showDate) {
		this.showDate = showDate;
	}

	/**
	 * @return the showTime
	 */
	public LocalTime getShowTimings() {
		return showTimings;
	}

	/**
	 * @param showTime the showTime to set
	 */
	public void setShowTimings(LocalTime showTimings) {
		this.showTimings = showTimings;
	}
	
	

}
