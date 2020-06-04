/**
 * 
 */
package com.springboot.justbook.searchservice.service;

import javax.validation.Valid;

import com.springboot.justbook.searchservice.vo.CinemasVO;
import com.springboot.justbook.searchservice.vo.JBTResponseObject;

/**
 * @author M1006601
 *
 */
public interface CinemasService {

	JBTResponseObject getAllCinemas();

	JBTResponseObject getCinemasByLocation(String cinemasLocation);
	
	JBTResponseObject findCinemasByCinemasId(Long cinemasId);

	JBTResponseObject findCinemasByCinemasName(String cinemasName);

	JBTResponseObject addCinemas(@Valid CinemasVO cinema);

}
