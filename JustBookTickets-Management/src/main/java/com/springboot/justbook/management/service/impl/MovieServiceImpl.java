/**
 * 
 */
package com.springboot.justbook.management.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.springboot.justbook.management.domain.Cinemas;
import com.springboot.justbook.management.domain.Movie;
import com.springboot.justbook.management.domain.MovieSchedule;
import com.springboot.justbook.management.mapper.MovieMgmtMapper;
import com.springboot.justbook.management.repository.CinemasRepository;
import com.springboot.justbook.management.repository.MovieRepository;
import com.springboot.justbook.management.repository.MovieScheduleRepository;
import com.springboot.justbook.management.service.MovieService;
import com.springboot.justbook.vo.MovieMgmtMovieVOResponseObject;
import com.springboot.justbook.vo.MovieVO;

/**
 * @author M1006601
 *
 */
@Service
public class MovieServiceImpl implements MovieService {
	
	/**
	 * LOGGER Logger object for logging in different logger Levels.
	 */
	static Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	MovieScheduleRepository movieScheduleRepository;
	
	@Autowired
	CinemasRepository cinemasRepository;
	
	@Autowired
	MovieMgmtMapper mapper;

	@Override
	public MovieMgmtMovieVOResponseObject getAllMovies() {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getAllMovies() method to fetch list of Movies");
		MovieMgmtMovieVOResponseObject response = new MovieMgmtMovieVOResponseObject();
		response.setResultList(mapper.mapMoviesToMovieVOList(movieRepository.findByIsMovieActiveTrue()));
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getAllMovies() method to fetch list of Movies");
		return response;
	}

	@Override
	public MovieMgmtMovieVOResponseObject getAllMoviesByLocation(String location) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getAllMoviesByLocation() method to fetch Movies by location");
		MovieMgmtMovieVOResponseObject response = new MovieMgmtMovieVOResponseObject();
		List<Movie> moviesList = new ArrayList<Movie>();
		List<Cinemas> cinemasList = cinemasRepository.findByCinemasLocation(location);
		List<MovieSchedule> scheduleList;
		if(!cinemasList.isEmpty()) {
			for(Cinemas cinemas: cinemasList) {
				scheduleList=movieScheduleRepository.getMovieScheduleByCinemasId(cinemas.getCinemasId());
				for(MovieSchedule movSchedule: scheduleList) {
					moviesList.add(movieRepository.getOne(movSchedule.getMovie().getMovieId()));
				}
			}
			response.setResultList(mapper.mapMoviesToMovieVOList(moviesList));
		}else {
			response.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
			response.setErrorCode("notFound");
			response.setDescription("No Cinemas found for the Location:"+location);
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getAllMoviesByLocation() method to fetch Movies by location");
		return response;
	}

	@Override
	public MovieMgmtMovieVOResponseObject getAllMoviesByCinemas(String cinemas) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getAllMoviesByCinemas() method to fetch Movies by cinemas");
		MovieMgmtMovieVOResponseObject response = new MovieMgmtMovieVOResponseObject();
		List<Movie> moviesList = new ArrayList<Movie>();
		List<MovieSchedule> scheduleList = new ArrayList<MovieSchedule>();
		List<Cinemas> cinemasList = cinemasRepository.findByCinemasName(cinemas);
		if (null != cinemasList) {
			for (Cinemas cinema : cinemasList) {
				scheduleList = movieScheduleRepository.getMovieScheduleByCinemasId(cinema.getCinemasId());
				for (MovieSchedule movSchedule : scheduleList) {
					moviesList.add(movieRepository.getOne(movSchedule.getMovie().getMovieId()));
				}
			}
			response.setResultList(mapper.mapMoviesToMovieVOList(moviesList));
		}else {
			response.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
			response.setErrorCode("notFound");
			response.setDescription("No Movies found for:"+cinemas);
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getAllMoviesByCinemas() method to fetch Movies by cinemas");	
		return response;
	}

	@Override
	public MovieMgmtMovieVOResponseObject getMoviesByLocationAndCinemas(String location, String cinemas) {

		LOGGER.debug("Entering the " + getClass().getSimpleName()
				+ " class & getMoviesByLocationAndCinemas() method to fetch Movies by location & cinemas");
		MovieMgmtMovieVOResponseObject response = new MovieMgmtMovieVOResponseObject();
		List<Movie> moviesList = new ArrayList<Movie>();
		List<MovieSchedule> scheduleList = new ArrayList<MovieSchedule>();
		Long cinemaId = null;
		List<Cinemas> cinemasList = cinemasRepository.findByCinemasLocation(location);
		if (!cinemasList.isEmpty()) {
			for (Cinemas cinema : cinemasList) {
				if (cinema.getCinemasName().equalsIgnoreCase(cinemas)) {
					cinemaId = cinema.getCinemasId();
					break;
				}
			}
			scheduleList = movieScheduleRepository.getMovieScheduleByCinemasId(cinemaId);
			for (MovieSchedule movSchedule : scheduleList) {
				moviesList.add(movieRepository.getOne(movSchedule.getMovie().getMovieId()));
			}
			response.setResultList(mapper.mapMoviesToMovieVOList(moviesList));
		} else {
			response.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
			response.setErrorCode("notFound");
			response.setDescription("No Movies found for cinemas: " + cinemas + " and location: " + location);
		}
		LOGGER.debug("Exiting the " + getClass().getSimpleName()
				+ " class & getMoviesByLocationAndCinemas() method to fetch Movies by location & cinemas");
		return response;
	}

	@Override
	public MovieMgmtMovieVOResponseObject addMovie(MovieVO movie) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & addMovie() method to add movie");
		MovieMgmtMovieVOResponseObject response = new MovieMgmtMovieVOResponseObject();
		Movie movieExisting = movieRepository.findMovieByMovieTitleAndIsMovieActiveTrue(movie.getMovieTitle());
		if(null==movieExisting) {
			Movie movieEntity = mapper.mapMovieVOToMovie(movie);
			movieRepository.save(movieEntity);
			response.setResultList(mapper.mapMovieToMovieVOList(movieEntity));
			response.setId(movieEntity.getMovieId());
			
		}else {
			response.setStatusCode(HttpServletResponse.SC_FOUND);
			response.setErrorCode("found");
			response.setDescription("Movie "+movie.getMovieTitle()+" already listed in Database");
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & addMovie() method to add movie");
		return response;
	}

	@Override
	public void deleteById(Long id) {
		Assert.notNull(id, "Movie id must not be null!");
		movieRepository.deleteByMovieId(id);		
	}
	
	@Override
	public MovieMgmtMovieVOResponseObject getMovieById(Long movieId) {

		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getMovieById() method to get movie details by movieId");
		MovieMgmtMovieVOResponseObject response = new MovieMgmtMovieVOResponseObject();
		Movie fetchedMovie = movieRepository.findMovieByMovieIdAndIsMovieActiveTrue(movieId);
		if(null!=fetchedMovie) {
			response.setResultList(mapper.mapMovieToMovieVOList(fetchedMovie));
		}else {
			response.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
			response.setErrorCode("notFound");
			response.setDescription("Movie is not listed in Database or Movie is not actively running");
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getMovieById() method to get movie details by movieId");
		return response;
	}

	@Override
	public MovieMgmtMovieVOResponseObject getMovieByName(String movieName) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getMovieByName() method to get movie details by movie name");
		MovieMgmtMovieVOResponseObject response = new MovieMgmtMovieVOResponseObject();
		Movie fetchedMovie = movieRepository.findMovieByMovieTitleAndIsMovieActiveTrue(movieName);
		if(null!=fetchedMovie) {
			response.setResultList(mapper.mapMovieToMovieVOList(fetchedMovie));
		}else {
			response.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
			response.setErrorCode("notFound");
			response.setDescription("Movie is not listed in Database or Movie is not actively running");
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getMovieByName() method to get movie details by movie name");
		return response;
	}

	
}
