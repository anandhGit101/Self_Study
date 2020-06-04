/**
 * 
 */
package com.springboot.justbook.searchservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.justbook.searchservice.domain.Movies;
import com.springboot.justbook.searchservice.mapper.MoviesMapper;
import com.springboot.justbook.searchservice.repository.MoviesELKRepository;
import com.springboot.justbook.searchservice.service.MoviesService;
import com.springboot.justbook.searchservice.vo.JBTResponseObject;
import com.springboot.justbook.searchservice.vo.MoviesVO;

/**
 * @author M1006601
 *
 */
@Service
public class MoviesServiceImpl implements MoviesService {
	
	@Autowired
    Client client;
	
	@Autowired
    MoviesELKRepository moviesELKRepo;
	
	@Autowired
	MoviesMapper mapper;

	@Override
	public JBTResponseObject getAllMovies() {
		
		JBTResponseObject moviesELKResObj = new JBTResponseObject();
		/*
		 * Iterable<Movies> moviesList = moviesELKRepo.findAll(); List<Movies>
		 * allMoviesList=new ArrayList<>(); for(Movies movies:moviesList) {
		 * allMoviesList.add(movies); }
		 */
		moviesELKResObj.setResultList(mapper.mapMoviesELKToMoviesELKVOList(moviesELKRepo.findAllMoviesByMovieActiveTrue()));
		return moviesELKResObj;
	}

	@Override
	public JBTResponseObject addMovie(@Valid MoviesVO movie) {

		JBTResponseObject moviesELKResObj = new JBTResponseObject();
		System.out.println(moviesELKRepo.findMovieByMovieTitle(movie.getMovieTitle()) + "Array");
		Movies findMovieByMovieTitleAndIsMovieActiveTrue = moviesELKRepo
				.findMovieByMovieTitle(movie.getMovieTitle());

		if (null == findMovieByMovieTitleAndIsMovieActiveTrue) {
			Movies newMovie = mapper.mapMoviesELKVOToMoviesELK(movie);

			newMovie = moviesELKRepo.save(newMovie);
			moviesELKResObj.setResultList(mapper.mapMoviesELKToMoviesELKVOList(newMovie));
			//moviesELKResObj.setId(newMovie.getMovieId());
		} else {
			moviesELKResObj.setStatusCode(HttpServletResponse.SC_FOUND);
			moviesELKResObj.setErrorCode("found");
			moviesELKResObj.setDescription("Movie " + movie.getMovieTitle() + " already listed in Database");
		}

		return moviesELKResObj;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public JBTResponseObject getMovieById(Long movieId) {
		
		JBTResponseObject moviesELKResObj = new JBTResponseObject();
		Movies findMovieByMovieIdAndMovieActiveTrue = moviesELKRepo.findMovieByMovieId(movieId);
		if(null!=findMovieByMovieIdAndMovieActiveTrue) {
			moviesELKResObj.setResultList(mapper.mapMoviesELKToMoviesELKVOList(findMovieByMovieIdAndMovieActiveTrue));
		}else {
			moviesELKResObj.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
			moviesELKResObj.setErrorCode("notFound");
			moviesELKResObj.setDescription("Movie is not listed in Database or Movie is not actively running");
		}
		return moviesELKResObj;
	}

	@Override
	public JBTResponseObject getMovieByName(String movieName) {
		
		JBTResponseObject moviesELKResObj = new JBTResponseObject();
		Movies findMovieByMovieTitleAndMovieActiveTrue = moviesELKRepo.findMovieByMovieTitle(movieName);
		System.out.println(findMovieByMovieTitleAndMovieActiveTrue);
		if(null!=findMovieByMovieTitleAndMovieActiveTrue) {
			moviesELKResObj.setResultList(mapper.mapMoviesELKToMoviesELKVOList(findMovieByMovieTitleAndMovieActiveTrue));
		}else {
			moviesELKResObj.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
			moviesELKResObj.setErrorCode("notFound");
			moviesELKResObj.setDescription("Movie is not listed in Database or Movie is not actively running");
		}
		return moviesELKResObj;
	}

}
