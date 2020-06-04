/**
 * 
 */
package com.springboot.justbook.management.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.justbook.management.domain.Movie;
import com.springboot.justbook.management.mapper.MovieMgmtMapper;
import com.springboot.justbook.management.repository.MovieRepository;
import com.springboot.justbook.management.service.impl.MovieServiceImpl;
import com.springboot.justbook.vo.MovieMgmtMovieVOResponseObject;
import com.springboot.justbook.vo.MovieVO;

/**
 * @author M1006601
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieServiceTest {

	@InjectMocks
	MovieServiceImpl movieService;
	
	@MockBean
	MovieRepository movieRepository;
	
	@Autowired
	MovieMgmtMapper mapper;
	
	@Test
	public void getAllMovies() {
		
		List<Movie> moviesList = getMovies();
		MovieMgmtMovieVOResponseObject response = new MovieMgmtMovieVOResponseObject();
		List<MovieVO> moviesVOList = mapper.mapMoviesToMovieVOList(moviesList);
		response.setResultList(moviesVOList);
		when(movieRepository.findByIsMovieActiveTrue()).thenReturn(moviesList);
		ResponseEntity<MovieMgmtMovieVOResponseObject> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		assertNotNull(responseEntity);
		assertNotNull(responseEntity.getBody().getResultList());
		assertEquals(2, responseEntity.getBody().getResultList().size());
	}
	
	@Test
	public void getMovieById() {
		
		Movie movie = getMovieByMovieId();
		MovieMgmtMovieVOResponseObject response = new MovieMgmtMovieVOResponseObject();
		List<MovieVO> moviesVOList = mapper.mapMovieToMovieVOList(movie);
		response.setResultList(moviesVOList);
		when(movieRepository.findMovieByMovieIdAndIsMovieActiveTrue(new Long(1))).thenReturn(movie);
		assertNotNull(response);
		assertNotNull(response.getResultList());
	}
	
	@Test
	public void getMovieByName() {
		
		Movie movie = getMovieByMovieName();
		MovieMgmtMovieVOResponseObject response = new MovieMgmtMovieVOResponseObject();
		List<MovieVO> moviesVOList = mapper.mapMovieToMovieVOList(movie);
		response.setResultList(moviesVOList);
		when(movieRepository.findMovieByMovieTitleAndIsMovieActiveTrue("Avengers - End Game")).thenReturn(movie);
		assertNotNull(response);
		assertNotNull(response.getResultList());
	}
	
	@Test
	public void addMovie() {
		
		Movie movie = getMovieByMovieName();
		MovieMgmtMovieVOResponseObject response = new MovieMgmtMovieVOResponseObject();
		List<MovieVO> moviesVOList = mapper.mapMovieToMovieVOList(movie);
		response.setResultList(moviesVOList);
		when(movieRepository.save(new Movie())).thenReturn(movie);
		assertNotNull(response);
		assertNotNull(response.getResultList());
		assertEquals(new Long(2),response.getResultList().get(0).getMovieId());
		
	}
	
	private Movie getMovieByMovieName() {
		
		Movie movie = new Movie();
		movie.setMovieActive(true);
		movie.setMovieId(new Long(2));
		movie.setMovieTitle("Avengers - End Game");
		movie.setMovieLanguage("English");
		movie.setMovieCast("Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth, Scarlett Johansson, Jeremy Renner");
		movie.setMovieGenre("American superhero film");
		movie.setMovieReleaseDate(LocalDate.now());
		movie.setMovieSynopsis("Avengers: Endgame is a 2019 American superhero film based on the Marvel Comics superhero team the Avengers, produced by Marvel Studios and distributed by Walt Disney Studios Motion Pictures.");
		return movie;
	}

	private Movie getMovieByMovieId() {
		
		Movie movie = new Movie();
		movie.setMovieActive(true);
		movie.setMovieId(new Long(1));
		movie.setMovieTitle("Avengers - Infinity War");
		movie.setMovieLanguage("English");
		movie.setMovieCast("Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth, Scarlett Johansson, Jeremy Renner");
		movie.setMovieGenre("American superhero film");
		movie.setMovieReleaseDate(LocalDate.now());
		movie.setMovieSynopsis("Avengers: Endgame is a 2019 American superhero film based on the Marvel Comics superhero team the Avengers, produced by Marvel Studios and distributed by Walt Disney Studios Motion Pictures.");
		return movie;
	}

	private List<Movie> getMovies() {
		
		List<Movie> allMoviesList = new ArrayList<>();
		
		Movie movie = new Movie();
		movie.setMovieActive(true);
		movie.setMovieId(new Long(1));
		movie.setMovieTitle("Avengers - End Game");
		movie.setMovieLanguage("English");
		movie.setMovieCast("Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth, Scarlett Johansson, Jeremy Renner");
		movie.setMovieGenre("American superhero film");
		movie.setMovieReleaseDate(LocalDate.now());
		movie.setMovieSynopsis("Avengers: Endgame is a 2019 American superhero film based on the Marvel Comics superhero team the Avengers, produced by Marvel Studios and distributed by Walt Disney Studios Motion Pictures.");
		allMoviesList.add(movie);
		
		movie = new Movie();
		movie.setMovieActive(true);
		movie.setMovieId(new Long(2));
		movie.setMovieTitle("Avengers - Infinity War");
		movie.setMovieLanguage("English");
		movie.setMovieCast("Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth, Scarlett Johansson, Jeremy Renner");
		movie.setMovieGenre("American superhero film");
		movie.setMovieReleaseDate(LocalDate.now());
		movie.setMovieSynopsis("Avengers:  Infinity War is a 2018 American superhero film based on the Marvel Comics superhero team the Avengers, produced by Marvel Studios and distributed by Walt Disney Studios Motion Pictures.");
		allMoviesList.add(movie);
		
		return allMoviesList;
	}
}
