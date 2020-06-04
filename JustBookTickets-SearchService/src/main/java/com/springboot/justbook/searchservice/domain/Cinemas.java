/**
 * 
 */
package com.springboot.justbook.searchservice.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author M1006601
 *
 */
@Entity
@Document(indexName = "mycinemaindex", type="cinemas")
public class Cinemas {
	
	@Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cinemas_id")
	private Long cinemasId;

	@Column(name="cinemas_name")
	private String cinemasName;

	@Column(name="cinemas_address")
	private String cinemasAddress;

	@Column(name="cinemas_location")
	private String cinemasLocation;
	
	@OneToMany(mappedBy="cinemas", fetch=FetchType.LAZY)
	private List<MovieSchedule> movieSchedule;

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

}
