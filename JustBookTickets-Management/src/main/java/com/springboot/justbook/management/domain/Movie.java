/**
 * 
 */
package com.springboot.justbook.management.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author M1006601
 *
 */
@Entity
@Table(name="movie")
@EntityListeners(AuditingEntityListener.class)
public class Movie extends AbstractAuditingEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2306031435895539328L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="movie_id")
	private Long movieId;
	
	@Column(name="movie_title")
	@NotNull
	@NotEmpty
	private String movieTitle;
	
	@NotNull
	@NotEmpty
	@Column(name="movie_cast")
	private String movieCast;
	
	@Lob
	@Column(name="movie_synopsis")
	private String movieSynopsis;
	
	@NotNull
	@NotEmpty
	@Column(name="movie_language")
	private String movieLanguage;
	
	@NotNull
	@Column(name="movie_release_date")
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate movieReleaseDate;
	
	@NotNull
	@NotEmpty
	@Column(name="movie_genre")
	private String movieGenre;
	
	@Column(name="movie_img")
	private String movieImgURL;
	
	@OneToMany(mappedBy="movie", fetch=FetchType.LAZY)
	private List<MovieSchedule> movieSchedule;
	
	@Column(name="movie_active", columnDefinition="boolean default true")
	private Boolean isMovieActive=true;
	
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
	 * @return the movieImgURL
	 */
	public String getMovieImgURL() {
		return movieImgURL;
	}

	/**
	 * @param movieImgURL the movieImgURL to set
	 */
	public void setMovieImgURL(String movieImgURL) {
		this.movieImgURL = movieImgURL;
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
	 * @return the isMovieActive
	 */
	public Boolean isMovieActive() {
		return isMovieActive;
	}

	/**
	 * @param isMovieActive the isMovieActive to set
	 */
	public void setMovieActive(Boolean isMovieActive) {
		this.isMovieActive = isMovieActive;
	}

	/**
	 * @param movieId
	 * @param movieTitle
	 * @param movieCast
	 * @param movieSynopsis
	 * @param movieLanguage
	 * @param movieReleaseDate
	 * @param movieGenre
	 * @param movieImgURL
	 */
	public Movie(Long movieId, String movieTitle, String movieCast,
			String movieSynopsis, String movieLanguage, LocalDate movieReleaseDate,
			String movieGenre, String movieImgURL, Boolean isMovieActive) {
		super();
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.movieCast = movieCast;
		this.movieSynopsis = movieSynopsis;
		this.movieLanguage = movieLanguage;
		this.movieReleaseDate = movieReleaseDate;
		this.movieGenre = movieGenre;
		this.movieImgURL = movieImgURL;
		this.isMovieActive = isMovieActive;
	}
	
	
	public Movie() {
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movieId == null) ? 0 : movieId.hashCode());
		result = prime * result + ((movieLanguage == null) ? 0 : movieLanguage.hashCode());
		result = prime * result + ((movieReleaseDate == null) ? 0 : movieReleaseDate.hashCode());
		result = prime * result + ((movieTitle == null) ? 0 : movieTitle.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (movieId == null) {
			if (other.movieId != null)
				return false;
		} else if (!movieId.equals(other.movieId))
			return false;
		if (movieLanguage == null) {
			if (other.movieLanguage != null)
				return false;
		} else if (!movieLanguage.equals(other.movieLanguage))
			return false;
		if (movieReleaseDate == null) {
			if (other.movieReleaseDate != null)
				return false;
		} else if (!movieReleaseDate.equals(other.movieReleaseDate))
			return false;
		if (movieTitle == null) {
			if (other.movieTitle != null)
				return false;
		} else if (!movieTitle.equals(other.movieTitle))
			return false;
		return true;
	}
}
