/**
 * 
 */
package com.springboot.justbook.searchservice.vo;

import org.elasticsearch.common.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author M1006601
 *
 */
public class MoviesVO implements GenericVO{

	@Nullable
	private Long movieId;

	private String movieTitle;

	private String movieCast;

	private String movieSynopsis;

	private String movieLanguage;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private String movieReleaseDate;

	private String movieGenre;

	private String movieImageURL;

	private Boolean movieActive;

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
	public String getMovieReleaseDate() {
		return movieReleaseDate;
	}

	/**
	 * @param movieReleaseDate the movieReleaseDate to set
	 */
	public void setMovieReleaseDate(String movieReleaseDate) {
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
	public String getMovieImageURL() {
		return movieImageURL;
	}

	/**
	 * @param imageURL the imageURL to set
	 */
	public void setMovieImageURL(String movieImageURL) {
		this.movieImageURL = movieImageURL;
	}

	/**
	 * @return the movieActive
	 */
	public Boolean getMovieActive() {
		return movieActive;
	}

	/**
	 * @param movieActive the movieActive to set
	 */
	public void setIsActive(Boolean movieActive) {
		this.movieActive = movieActive;
	}

	public MoviesVO() {
		super();
	}

	/**
	 * @param movieId
	 * @param movieTitle
	 * @param movieCast
	 * @param movieSynopsis
	 * @param movieLanguage
	 * @param movieReleaseDate
	 * @param movieGenre
	 * @param imageURL
	 * @param movieActive
	 */
	public MoviesVO(Long movieId, String movieTitle, String movieCast, String movieSynopsis, String movieLanguage,
			String movieReleaseDate, String movieGenre, String movieImageURL, Boolean movieActive) {
		super();
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.movieCast = movieCast;
		this.movieSynopsis = movieSynopsis;
		this.movieLanguage = movieLanguage;
		this.movieReleaseDate = movieReleaseDate;
		this.movieGenre = movieGenre;
		this.movieImageURL = movieImageURL;
		this.movieActive = movieActive;
	}

}
