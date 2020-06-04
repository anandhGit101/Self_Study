/**
 * 
 */
package com.springboot.justbook.vo;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author M1006601
 *
 */
public class MovieVO {

	private Long movieId;
	
	@NotNull
	@NotEmpty(message="Movie Title cannot be empty")
	private String movieTitle;
	
	@NotNull
	@NotEmpty
	private String movieCast;
	
	private String movieSynopsis;

	@NotNull
	@NotEmpty
	private String movieLanguage;

	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate movieReleaseDate;
	
	@NotNull
	@NotEmpty
	private String movieGenre;
	
	private String imageURL;
	
	@JsonIgnore
	private Boolean isActive;
	
	/**
	 * @param movieId
	 * @param movieTitle
	 * @param movieCast
	 * @param movieSynopsis
	 * @param movieLanguage
	 * @param movieReleaseDate
	 * @param movieGenre
	 */
	public MovieVO(Long movieId, String movieTitle, String movieCast, String movieSynopsis, String movieLanguage,
			LocalDate movieReleaseDate, String movieGenre, String imageURL, Boolean isActive) {
		super();
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.movieCast = movieCast;
		this.movieSynopsis = movieSynopsis;
		this.movieLanguage = movieLanguage;
		this.movieReleaseDate = movieReleaseDate;
		this.movieGenre = movieGenre;
		this.imageURL = imageURL;
		this.isActive = isActive;
	}

	public MovieVO() {
		
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
	 * @return the movieSynopsis
	 */
	public String getMovieSynopsis() {
		return movieSynopsis;
	}

	/**
	 * @param movieSynopsis the movieSynopsis to set
	 */
	public void setMovieSynopsis(String movieSynopsis) {
		this.movieSynopsis = movieSynopsis;
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
	 * @return the imageURL
	 */
	public String getImageURL() {
		return imageURL;
	}

	/**
	 * @param imageURL the imageURL to set
	 */
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	/**
	 * @return the isActive
	 */
	public Boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
