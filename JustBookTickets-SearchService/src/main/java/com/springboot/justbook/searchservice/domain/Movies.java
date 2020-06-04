/**
 * 
 */
package com.springboot.justbook.searchservice.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.springboot.justbook.searchservice.util.LocalDateDeserializer;
import com.springboot.justbook.searchservice.util.LocalDateSerializer;

/**
 * @author M1006601
 *
 */
@Entity
@Document(indexName = "myindex", type="movies")
public class Movies {
	
	@Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="movie_id")
	private Long movieId;
	
	@Column(name="movie_title")
	private String movieTitle;
	
	@Column(name="movie_cast")
	private String movieCast;
	
	@Column(name="movie_synopsis")
	private String movieSynopsis;
	
	@Column(name="movie_language")
	private String movieLanguage;
	
	@Column(name="movie_release_date")
	private LocalDate movieReleaseDate;

	@Column(name="movie_genre")
	private String movieGenre;
	
	@Column(name="movie_img")
	private String movieImageURL;
	
	@OneToMany(mappedBy="movie", fetch=FetchType.LAZY)
	private List<MovieSchedule> movieSchedule;
	
	@Column(name="movie_active", columnDefinition="boolean default true")
	private Boolean movieActive;

	/**
	 * @param movieId
	 * @param movieTitle
	 * @param movieCast
	 * @param movieSynopsis
	 * @param movieLanguage
	 * @param movieReleaseDate
	 * @param movieGenre
	 * @param movieImgURL
	 * @param isMovieActive
	 */
	public Movies(Long movieId, String movieTitle, String movieCast, String movieSynopsis,
			String movieLanguage, LocalDate movieReleaseDate, String movieGenre, String movieImgURL,
			Boolean movieActive) {
		super();
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.movieCast = movieCast;
		this.movieSynopsis = movieSynopsis;
		this.movieLanguage = movieLanguage;
		this.movieReleaseDate = movieReleaseDate;
		this.movieGenre = movieGenre;
		this.movieImageURL = movieImgURL;
		this.movieActive = movieActive;
	}

	public Movies() {
		super();
	}

	@Override
	public String toString() {
		return "Movies [movieId=" + movieId + ", movieTitle=" + movieTitle + ", movieCast=" + movieCast
				+ ", movieSynopsis=" + movieSynopsis + ", movieLanguage=" + movieLanguage + ", movieReleaseDate="
				+ movieReleaseDate + ", movieGenre=" + movieGenre + ", movieImgURL=" + movieImageURL + ", MovieActive="
				+ movieActive + "]";
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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getMovieReleaseDate() {
		return movieReleaseDate;
	}

	/**
	 * @param movieReleaseDate the movieReleaseDate to set
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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
	 * @return the movieImgURL
	 */
	public String getMovieImageURL() {
		return movieImageURL;
	}

	/**
	 * @param movieImgURL the movieImgURL to set
	 */
	public void setMovieImageURL(String movieImgURL) {
		this.movieImageURL = movieImgURL;
	}

	/**
	 * @return the movieSchedule
	 */
	public List<MovieSchedule> getMovieSchedule() {
		return movieSchedule;
	}

	/**
	 * @param movieSchedule the movieSchedule to set
	 */
	public void setMovieSchedule(List<MovieSchedule> movieSchedule) {
		this.movieSchedule = movieSchedule;
	}

	/**
	 * @return the MovieActive
	 */
	public Boolean getMovieActive() {
		return movieActive;
	}

	/**
	 * @param movieActive to set the MovieActive
	 */
	public void setMovieActive(Boolean movieActive) {
		this.movieActive = movieActive;
	}
	
	
}
