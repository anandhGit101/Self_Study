/**
 * 
 */
package com.springboot.justbook.vo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author M1006601
 *
 */
public class MovieScheduleVO {

	private  Long movieId;
	
	private String movieTitle;
	
	private String movieCast;
	
	private String movieLanguage;

	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate movieReleaseDate;
	
	private String movieGenre;
	
	private Long cinemasId;
	
	private String cinemasName;
	
	private String cinemasAddress;

	private String cinemasLocation;
	
	 @JsonFormat(pattern="HH:mm:ss")
	private List<LocalTime> showTimingsList;
	
	@JsonFormat(pattern="dd-MMM-yyyy")
	private LocalDate showDate;

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
	 * @return the movieTitle
	 */
	public String getMovieTitle() {
		return movieTitle;
	}

	/**
	 * @param movieTitle the movieTitle to set
	 */
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	/**
	 * @return the movieCast
	 */
	public String getMovieCast() {
		return movieCast;
	}

	/**
	 * @param movieCast the movieCast to set
	 */
	public void setMovieCast(String movieCast) {
		this.movieCast = movieCast;
	}

	/**
	 * @return the movieLanguage
	 */
	public String getMovieLanguage() {
		return movieLanguage;
	}

	/**
	 * @param movieLanguage the movieLanguage to set
	 */
	public void setMovieLanguage(String movieLanguage) {
		this.movieLanguage = movieLanguage;
	}

	/**
	 * @return the movieReleaseDate
	 */
	public LocalDate getMovieReleaseDate() {
		return movieReleaseDate;
	}

	/**
	 * @param movieReleaseDate the movieReleaseDate to set
	 */
	public void setMovieReleaseDate(LocalDate movieReleaseDate) {
		this.movieReleaseDate = movieReleaseDate;
	}

	/**
	 * @return the movieGenre
	 */
	public String getMovieGenre() {
		return movieGenre;
	}

	/**
	 * @param movieGenre the movieGenre to set
	 */
	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}

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
	 * @return the cinemasName
	 */
	public String getCinemasName() {
		return cinemasName;
	}

	/**
	 * @param cinemasName the cinemasName to set
	 */
	public void setCinemasName(String cinemasName) {
		this.cinemasName = cinemasName;
	}

	/**
	 * @return the cinemasAddress
	 */
	public String getCinemasAddress() {
		return cinemasAddress;
	}

	/**
	 * @param cinemasAddress the cinemasAddress to set
	 */
	public void setCinemasAddress(String cinemasAddress) {
		this.cinemasAddress = cinemasAddress;
	}

	/**
	 * @return the cinemasLocation
	 */
	public String getCinemasLocation() {
		return cinemasLocation;
	}

	/**
	 * @param cinemasLocation the cinemasLocation to set
	 */
	public void setCinemasLocation(String cinemasLocation) {
		this.cinemasLocation = cinemasLocation;
	}

	/**
	 * @return the showTimingsList
	 */
	public List<LocalTime> getShowTimingsList() {
		return showTimingsList;
	}

	/**
	 * @param showTimingsList the showTimingsList to set
	 */
	public void setShowTimingsList(List<LocalTime> showTimings) {
		this.showTimingsList = showTimings;
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
	
	
}
