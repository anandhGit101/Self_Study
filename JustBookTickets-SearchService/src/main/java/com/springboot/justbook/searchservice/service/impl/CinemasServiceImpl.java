/**
 * 
 */
package com.springboot.justbook.searchservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.justbook.searchservice.domain.Cinemas;
import com.springboot.justbook.searchservice.mapper.MoviesMapper;
import com.springboot.justbook.searchservice.repository.CinemasELKRepository;
import com.springboot.justbook.searchservice.service.CinemasService;
import com.springboot.justbook.searchservice.vo.CinemasVO;
import com.springboot.justbook.searchservice.vo.JBTResponseObject;

/**
 * @author M1006601
 *
 */
@Service
public class CinemasServiceImpl implements CinemasService {

	@Autowired
	CinemasELKRepository cinemasRepository;
	
	@Autowired
	MoviesMapper mapper;
	
	@Override
	public JBTResponseObject getAllCinemas() {
		
		JBTResponseObject  response = new JBTResponseObject();
		List<Cinemas> cinemasList = new ArrayList<Cinemas>();
	    for (Cinemas cinemasIn : cinemasRepository.findAll()) {
	    	cinemasList.add(cinemasIn);
	    }		
		response.setResultList(mapper.mapCinemasToCinemasVOList(cinemasList));
		return response;
	}

	@Override
	public JBTResponseObject getCinemasByLocation(String cinemasLocation) {

		JBTResponseObject  response = new JBTResponseObject();
		List<Cinemas> cinemasList = cinemasRepository.findCinemasByCinemasLocation(cinemasLocation);
		if(null!=cinemasList) {
			response.setResultList(mapper.mapCinemasToCinemasVOList(cinemasList));
		}else {
			response.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
			response.setErrorCode("notFound");
			response.setDescription("No Cinemas found for the :"+cinemasLocation);
		}
		return response;
	}

	@Override
	public JBTResponseObject findCinemasByCinemasId(Long cinemasId) {
		
		JBTResponseObject  response = new JBTResponseObject();
		Cinemas cinemasExisting = cinemasRepository.findCinemasByCinemasId(cinemasId);
		if(null!=cinemasExisting) {
			response.setResultList(mapper.mapCinemasToCinemaVOList(cinemasExisting));
		}else {
			response.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
			response.setErrorCode("notFound");
			response.setDescription("No Cinemas found with the cinemaId:"+cinemasId);
		}
		return response;
	}

	@Override
	public JBTResponseObject findCinemasByCinemasName(String cinemasName) {

		JBTResponseObject  response = new JBTResponseObject();
		Cinemas cinemasExisting = cinemasRepository.findCinemasByCinemasName(cinemasName);
		if(null!=cinemasExisting) {
			response.setResultList(mapper.mapCinemasToCinemaVOList(cinemasExisting));
		}else {
			response.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
			response.setErrorCode("notFound");
			response.setDescription("No Cinemas found with the cinema name:"+cinemasName);
		}
		return response;
	}

	@Override
	public JBTResponseObject addCinemas(@Valid CinemasVO cinema) {
		
		JBTResponseObject  response = new JBTResponseObject();
		Cinemas cinemasExisting = cinemasRepository.findCinemasByCinemasName(cinema.getCinemasName());
		if(null==cinemasExisting) {
			Cinemas cinemas = mapper.mapCinemasELKVOToCinemas(cinema);
			cinemas = cinemasRepository.save(cinemas);
		}
		return response;
	}
	
	
}
