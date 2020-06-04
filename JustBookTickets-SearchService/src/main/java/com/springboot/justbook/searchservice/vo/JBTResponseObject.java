/**
 * 
 */
package com.springboot.justbook.searchservice.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author M1006601
 *
 */
public class JBTResponseObject {

	private long id;

	private String errorCode;

	private int statusCode;

	private String description;

	@JsonInclude(value = Include.NON_NULL)
	private List<GenericVO> resultList;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the resultList
	 */
	public List<GenericVO> getResultList() {
		return resultList;
	}

	/**
	 * @param resultList the resultList to set
	 */
	public void setResultList(List<GenericVO> resultList) {
		this.resultList = resultList;
	}

}