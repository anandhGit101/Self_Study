/**
 * 
 */
package com.springboot.justbook.management.service;

import javax.validation.Valid;

import com.springboot.justbook.vo.CinemasVO;
import com.springboot.justbook.vo.MovieMgmtCinemasVOResponseObject;

/**
 * @author M1006601
 *
 */
public interface CinemasService {
	
	public MovieMgmtCinemasVOResponseObject getAllCinemas();
	
	public MovieMgmtCinemasVOResponseObject getAllCinemasByCinemasLocation(String location);
	
	public MovieMgmtCinemasVOResponseObject getCinemasByCinemasName(String cinemasName);

	public MovieMgmtCinemasVOResponseObject addCinemas(@Valid CinemasVO cinema);

	public void deleteByCinemasId(Long id);

	public MovieMgmtCinemasVOResponseObject getCinemasById(Long cinemasId);

}
