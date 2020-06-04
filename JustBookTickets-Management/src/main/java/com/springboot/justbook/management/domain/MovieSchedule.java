/**
 * 
 */
package com.springboot.justbook.management.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author M1006601
 *
 */
@Entity
@Table(name="movie_schedule")
@EntityListeners(AuditingEntityListener.class)
public class MovieSchedule extends AbstractAuditingEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8072009529034562021L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="movie_schedule_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="movie_id", nullable=false)
	private Movie movie;
	
	@JoinColumn(name="cinemas_id", nullable=false)
	@ManyToOne
	private Cinemas cinemas;
	
	@Column(name="show_timings")
	@JsonFormat(pattern="HH:mm:ss")
	private LocalTime showTimings;
	
	@Column(name="show_date")
	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate showDate;
	
	@Column(name="tickets_available", columnDefinition="integer default 60")
	private Integer ticketsAvailable;
	
	
	
	/**
	 * @param id
	 * @param movie
	 * @param cinemas
	 * @param showTimings
	 * @param showDate
	 * @param ticketsAvailable
	 */
	public MovieSchedule(Long id, Movie movie, Cinemas cinemas, LocalTime showTimings, LocalDate showDate,
			int ticketsAvailable) {
		super();
		this.id = id;
		this.movie = movie;
		this.cinemas = cinemas;
		this.showTimings = showTimings;
		this.showDate = showDate;
		this.ticketsAvailable = ticketsAvailable;
	}

	/**
	 * 
	 */
	public MovieSchedule() {
		super();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	
	/**
	 * @return the movie
	 */
	public Movie getMovie() {
		return movie;
	}

	/**
	 * @param movie the movie to set
	 */
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	/**
	 * @return the cinemas
	 */
	public Cinemas getCinemas() {
		return cinemas;
	}

	/**
	 * @param cinemas the cinemas to set
	 */
	public void setCinemas(Cinemas cinemas) {
		this.cinemas = cinemas;
	}

	/**
	 * @return the showTimings
	 */
	public LocalTime getShowTimings() {
		return showTimings;
	}

	/**
	 * @param showTimings the showTimings to set
	 */
	public void setShowTimings(LocalTime showTimings) {
		this.showTimings = showTimings;
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
	 * @return the ticketsAvailable
	 */
	public Integer getTicketsAvailable() {
		return ticketsAvailable;
	}

	/**
	 * @param ticketsAvailable the ticketsAvailable to set
	 */
	public void setTicketsAvailable(Integer ticketsAvailable) {
		this.ticketsAvailable = ticketsAvailable;
	}
	
	

}
