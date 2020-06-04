/**
 * 
 */
package com.springboot.justbook.management.service;

import javax.validation.Valid;

import com.springboot.justbook.vo.MovieMgmtMovieVOResponseObject;
import com.springboot.justbook.vo.MovieVO;

/**
 * @author M1006601
 *
 */
public interface MovieService {
	
	public MovieMgmtMovieVOResponseObject getAllMovies();
	
	public MovieMgmtMovieVOResponseObject getAllMoviesByLocation(String location);
	
	public MovieMgmtMovieVOResponseObject getAllMoviesByCinemas(String cinemas);
	
	public MovieMgmtMovieVOResponseObject getMoviesByLocationAndCinemas(String location, String cinemas);

	public MovieMgmtMovieVOResponseObject addMovie(@Valid MovieVO movie);

	public void deleteById(Long id);

	public MovieMgmtMovieVOResponseObject getMovieById(Long movieId);

	public MovieMgmtMovieVOResponseObject getMovieByName(String movieName);

}
