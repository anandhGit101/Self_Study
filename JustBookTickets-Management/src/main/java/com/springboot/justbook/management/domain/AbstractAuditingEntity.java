/**
 * 
 */
package com.springboot.justbook.management.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.springboot.justbook.management.mapper.LocalDateTimeConverter;

/**
 * @author M1006601
 *
 */
@MappedSuperclass
@Audited
@EntityListeners(AuditingEntityListener.class)
public class AbstractAuditingEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@CreatedBy
	@Column(name = "created_by",  columnDefinition="varchar(255) default 'Admin_786'", length = 50, updatable = false)
	@NotNull
	private String createdBy = "Admin_786";

	@CreatedDate
	@Column(name = "created_date", nullable = false)
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime createdDate = LocalDateTime.now();

	@LastModifiedBy
	@Column(name = "last_modified_by", length = 50)
	private String lastModifiedBy;

	@LastModifiedDate
	@Column(name = "last_modified_date")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime lastModifiedDate = LocalDateTime.now();

}
