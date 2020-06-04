/**
 * 
 */
package com.springboot.justbook.searchservice.controller;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.justbook.searchservice.service.MoviesService;
import com.springboot.justbook.searchservice.vo.JBTResponseObject;
import com.springboot.justbook.searchservice.vo.MoviesVO;

/**
 * @author M1006601
 *
 */
@RestController
@RequestMapping("/jbt")
public class JBTELKMovieController {
	
	@Autowired
	Client client;
	
	@Autowired
	MoviesService moviesService;
	
	@GetMapping(path="/movies/lists", produces = "application/json")
	public ResponseEntity<JBTResponseObject> getMoviesList() throws Exception{

		ResponseEntity<JBTResponseObject> responseObj = null;
		JBTResponseObject moviesELKResObj = moviesService.getAllMovies();

		switch (moviesELKResObj.getStatusCode()) {
		case HttpServletResponse.SC_NOT_FOUND:
			responseObj = new ResponseEntity<JBTResponseObject>(moviesELKResObj, HttpStatus.NOT_FOUND);
			break;
		default:
			responseObj = new ResponseEntity<JBTResponseObject>(moviesELKResObj, HttpStatus.OK);
		}
		return responseObj;
	}
	
	@GetMapping(path = "/movie/listbyid", produces = "application/json")
	public ResponseEntity<JBTResponseObject> getMovieById(@RequestParam Long movieId)
			throws Exception {

		ResponseEntity<JBTResponseObject> responseObj = null;
		JBTResponseObject moviesELKResObj = moviesService.getMovieById(movieId);
		
		switch (moviesELKResObj.getStatusCode()) {
		case HttpServletResponse.SC_NOT_FOUND:
			responseObj = new ResponseEntity<JBTResponseObject>(moviesELKResObj, HttpStatus.NOT_FOUND);
			break;
		default:
			responseObj = new ResponseEntity<JBTResponseObject>(moviesELKResObj, HttpStatus.OK);
		}
		return responseObj;
	}
	
	@GetMapping(path = "/movie/listbyname", produces = "application/json")
	public ResponseEntity<JBTResponseObject> getMovieByName(@RequestParam String movieName)
			throws Exception {

		ResponseEntity<JBTResponseObject> responseObj = null;
		JBTResponseObject moviesELKResObj = moviesService.getMovieByName(movieName);
		
		switch (moviesELKResObj.getStatusCode()) {
		case HttpServletResponse.SC_NOT_FOUND:
			responseObj = new ResponseEntity<JBTResponseObject>(moviesELKResObj, HttpStatus.NOT_FOUND);
			break;
		default:
			responseObj = new ResponseEntity<JBTResponseObject>(moviesELKResObj, HttpStatus.OK);
		}
		return responseObj;
	}
	
	@PostMapping(path = "/movie", consumes = "application/json", produces = "application/json")
	public ResponseEntity<JBTResponseObject> addMovieELK(@Valid @RequestBody MoviesVO movie, HttpServletResponse response)
			throws Exception {
				
		ResponseEntity<JBTResponseObject> responseObj = null;
		JBTResponseObject addMovieELKResponse = moviesService.addMovie(movie);
		switch (addMovieELKResponse.getStatusCode()) {
			case HttpServletResponse.SC_FOUND:
				responseObj = new ResponseEntity<JBTResponseObject>(addMovieELKResponse, HttpStatus.BAD_REQUEST);
			break;
		default:
			URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/getMovieById/{id}")
			.buildAndExpand(addMovieELKResponse.getId()).toUri();
			response.setHeader("Location", location.toString());
			responseObj = new ResponseEntity<JBTResponseObject>(addMovieELKResponse, HttpStatus.CREATED);
		}
		return responseObj;		
	}
}