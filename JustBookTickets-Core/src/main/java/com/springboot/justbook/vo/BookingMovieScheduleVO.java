/**
 * 
 */
package com.springboot.justbook.vo;

/**
 * @author M1006601
 *
 */
public class BookingMovieScheduleVO {
	
	private String movieId;
	private String cinemasId;
	private String showDate;
	private String showTimings;
	/**
	 * @return the movieId
	 */
	public String getMovieId() {
		return movieId;
	}
	/**
	 * @param movieId the movieId to set
	 */
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	/**
	 * @return the cinemaId
	 */
	public String getCinemasId() {
		return cinemasId;
	}
	/**
	 * @param cinemaId the cinemaId to set
	 */
	public void setCinemasId(String cinemaId) {
		this.cinemasId = cinemaId;
	}
	/**
	 * @return the showDate
	 */
	public String getShowDate() {
		return showDate;
	}
	/**
	 * @param showDate the showDate to set
	 */
	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}
	/**
	 * @return the showTime
	 */
	public String getShowTimings() {
		return showTimings;
	}
	/**
	 * @param showTime the showTime to set
	 */
	public void setShowTimings(String showTimings) {
		this.showTimings = showTimings;
	}
}
