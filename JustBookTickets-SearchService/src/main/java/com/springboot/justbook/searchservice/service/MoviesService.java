/**
 * 
 */
package com.springboot.justbook.searchservice.service;

import javax.validation.Valid;

import com.springboot.justbook.searchservice.vo.JBTResponseObject;
import com.springboot.justbook.searchservice.vo.MoviesVO;

/**
 * @author M1006601
 *
 */
public interface MoviesService {
	
	public JBTResponseObject getAllMovies();
	
	public JBTResponseObject addMovie(@Valid MoviesVO movie);

	public void deleteById(Long id);

	public JBTResponseObject getMovieById(Long movieId);

	public JBTResponseObject getMovieByName(String movieName);

}
