/**
 * 
 */
package com.springboot.justbook.management.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.springboot.justbook.management.domain.Cinemas;
import com.springboot.justbook.management.mapper.MovieMgmtMapper;
import com.springboot.justbook.management.repository.CinemasRepository;
import com.springboot.justbook.management.service.CinemasService;
import com.springboot.justbook.vo.CinemasVO;
import com.springboot.justbook.vo.MovieMgmtCinemasVOResponseObject;

/**
 * @author M1006601
 *
 */
@Service
public class CinemasServiceImpl implements CinemasService {
	
	/**
	 * LOGGER Logger object for logging in different logger Levels.
	 */
	static Logger LOGGER = LoggerFactory.getLogger(CinemasServiceImpl.class);
	 
	@Autowired
	CinemasRepository cinemasRepository;
	
	@Autowired
	MovieMgmtMapper mapper;
	
	@Override
	public MovieMgmtCinemasVOResponseObject getAllCinemas() {
		
		MovieMgmtCinemasVOResponseObject response = new MovieMgmtCinemasVOResponseObject();
		response.setResultList(mapper.mapCinemasToCinemaVOList(cinemasRepository.findAll()));
		return response;
	}

	@Override
	public MovieMgmtCinemasVOResponseObject getAllCinemasByCinemasLocation(String location) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class "+getClass().getEnclosingMethod());
		MovieMgmtCinemasVOResponseObject response = new MovieMgmtCinemasVOResponseObject();
		List<Cinemas> cinemasList = cinemasRepository.findAllByCinemasLocation(location);
		if(!cinemasList.isEmpty()) {
			response.setResultList(mapper.mapCinemasToCinemaVOList(cinemasList));
		}else {
			response.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
			response.setErrorCode("notFound");
			response.setDescription("No Cinemas found for the :"+location);
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class "+getClass().getEnclosingMethod());
		return response;
	}

	@Override
	public MovieMgmtCinemasVOResponseObject getCinemasByCinemasName(String name) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getCinemasByCinemasName() method with name:"+name);
		MovieMgmtCinemasVOResponseObject response = new MovieMgmtCinemasVOResponseObject();
		List<Cinemas> cinemas= cinemasRepository.findByCinemasName(name);
		if(null!=cinemas) {
			response.setResultList(mapper.mapCinemasToCinemaVOList(cinemas));
		}else {
			response.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
			response.setErrorCode("notFound");
			response.setDescription("No Cinemas found with the name:"+name);
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getCinemasByCinemasName() method with name:"+name);
		return response;
	}

	@Override
	public MovieMgmtCinemasVOResponseObject addCinemas(CinemasVO cinemas) {

		MovieMgmtCinemasVOResponseObject response = new MovieMgmtCinemasVOResponseObject();
		Cinemas cinemasExisting = cinemasRepository.findCinemasByCinemasName(cinemas.getCinemasName());
		if(null==cinemasExisting) {
			Cinemas cinemasEntity = mapper.mapCinemasVOToCinema(cinemas);
			cinemasRepository.save(cinemasEntity);
			response.setId(cinemasEntity.getCinemasId());
		}else {
			response.setStatusCode(HttpServletResponse.SC_FOUND);
			response.setErrorCode("found");
			response.setDescription("Cinemas "+cinemas.getCinemasName()+" is already listed in Database");
		}
		return response;
	}

	@Override
	public void deleteByCinemasId(Long id) {
		Assert.notNull(id, "Cinemas id must not be null!");
		cinemasRepository.deleteByCinemasId(id);		
	}

	@Override
	public MovieMgmtCinemasVOResponseObject getCinemasById(Long cinemasId) {
		
		MovieMgmtCinemasVOResponseObject response = new MovieMgmtCinemasVOResponseObject();
		Cinemas cinemasExisting = cinemasRepository.findCinemasByCinemasId(cinemasId);
		if(null!=cinemasExisting) {
			response.setResultList(mapper.mapCinemasToCinemaVOList(cinemasExisting));
		}else {
			response.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
			response.setErrorCode("notFound");
			response.setDescription("No Cinemas found with the cinemaId:"+cinemasId);
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class "+getClass().getEnclosingMethod());
		return response;
	}
	
	

}
