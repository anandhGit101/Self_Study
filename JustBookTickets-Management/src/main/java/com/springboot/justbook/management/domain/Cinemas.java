/**
 * 
 */
package com.springboot.justbook.management.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author M1006601
 *
 */
@Entity
@Table(name="cinemas")
@EntityListeners(AuditingEntityListener.class)
public class Cinemas extends AbstractAuditingEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4215171665989911947L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cinemas_id")
	private Long cinemasId;
	
	@NotNull
	@NotEmpty
	@Column(name="cinemas_name")
	private String cinemasName;
	
	@NotNull
	@NotEmpty
	@Column(name="cinemas_address")
	private String cinemasAddress;
	
	@NotNull
	@NotEmpty
	@Column(name="cinemas_location")
	private String cinemasLocation;
	
	@OneToMany(mappedBy="cinemas", fetch=FetchType.LAZY)
	private List<MovieSchedule> movieSchedule;
	
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

	public Cinemas() {
		
	}
	
	/**
	 * @param cinemasId
	 * @param cinemasName
	 * @param cinemasAddress
	 * @param cinemasLocation
	 */
	public Cinemas(Long cinemasId, String cinemasName, String cinemasAddress, String cinemasLocation) {
		super();
		this.cinemasId = cinemasId;
		this.cinemasName = cinemasName;
		this.cinemasAddress = cinemasAddress;
		this.cinemasLocation = cinemasLocation;
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
	 * @return the cinemaLocation
	 */
	public String getCinemasLocation() {
		return cinemasLocation;
	}

	/**
	 * @param cinemaLocation the cinemaLocation to set
	 */
	public void setCinemasLocation(String cinemasLocation) {
		this.cinemasLocation = cinemasLocation;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cinemasLocation == null) ? 0 : cinemasLocation.hashCode());
		result = prime * result + ((cinemasId == null) ? 0 : cinemasId.hashCode());
		result = prime * result + ((cinemasName == null) ? 0 : cinemasName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cinemas other = (Cinemas) obj;
		if (cinemasLocation == null) {
			if (other.cinemasLocation != null)
				return false;
		} else if (!cinemasLocation.equals(other.cinemasLocation))
			return false;
		if (cinemasId == null) {
			if (other.cinemasId != null)
				return false;
		} else if (!cinemasId.equals(other.cinemasId))
			return false;
		if (cinemasName == null) {
			if (other.cinemasName != null)
				return false;
		} else if (!cinemasName.equals(other.cinemasName))
			return false;
		return true;
	}
	
	
}
