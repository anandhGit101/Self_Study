/**
 * 
 */
package com.springboot.justbook.management.controller;

import java.net.URI;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.justbook.management.exception.MovieMgmtException;
import com.springboot.justbook.management.service.CinemasService;
import com.springboot.justbook.management.service.MovieScheduleService;
import com.springboot.justbook.management.service.MovieService;
import com.springboot.justbook.vo.CinemasVO;
import com.springboot.justbook.vo.MovieMgmtCinemasVOResponseObject;
import com.springboot.justbook.vo.MovieMgmtMovieScheduleVOResponseObject;
import com.springboot.justbook.vo.MovieMgmtMovieVOResponseObject;
import com.springboot.justbook.vo.MovieScheduleRequestVO;
import com.springboot.justbook.vo.MovieVO;

import io.swagger.annotations.ApiOperation;

/**
 * @author M1006601
 *
 */
@RestController
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8899" }) 
public class MovieManagementController {
	
	/**
	 * LOGGER Logger object for logging in different logger Levels.
	 */
	static Logger LOGGER = LoggerFactory.getLogger(MovieManagementController.class);
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	CinemasService cinemasService;
	
	@Autowired
	MovieScheduleService scheduleService;
	
	@GetMapping(path="/movies/list", produces = "application/json")
	@ApiOperation(value = "Get all Movies", response=MovieMgmtMovieVOResponseObject.class)
	public ResponseEntity<MovieMgmtMovieVOResponseObject> chooseMovie() throws Exception{
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & chooseMovie() method to fetch list of Movies");		
		ResponseEntity<MovieMgmtMovieVOResponseObject> responseObj = null;
		MovieMgmtMovieVOResponseObject response = movieService.getAllMovies();
			
		switch (response.getStatusCode()) {
		case HttpServletResponse.SC_NOT_FOUND:
			responseObj = new ResponseEntity<MovieMgmtMovieVOResponseObject>(response, HttpStatus.NOT_FOUND);
			break;
		default:
			responseObj = new ResponseEntity<MovieMgmtMovieVOResponseObject>(response, HttpStatus.OK);
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & chooseMovie() method to fetch list of Movies");
		return responseObj;
	}
	
	@GetMapping(path="/movie/listbyname", produces = "application/json")
	@ApiOperation(value = "Get Movie detaiils by Movie Name", response=MovieMgmtMovieVOResponseObject.class)
	public ResponseEntity<MovieMgmtMovieVOResponseObject> getMovieByName(@RequestParam String movieName) throws Exception{
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getMovieByName() method to fetch Movie by Name");	
		MovieMgmtMovieVOResponseObject response = movieService.getMovieByName(movieName);
		ResponseEntity<MovieMgmtMovieVOResponseObject> responseObj = null;
		switch (response.getStatusCode()) {
		case HttpServletResponse.SC_NOT_FOUND:
			responseObj = new ResponseEntity<MovieMgmtMovieVOResponseObject>(response, HttpStatus.NOT_FOUND);
			break;
		default:
			responseObj = new ResponseEntity<MovieMgmtMovieVOResponseObject>(response, HttpStatus.OK);
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getMovieByName() method to fetch Movie by Name");		
		return responseObj;
	}
	
	
	@GetMapping(path="/cinemas", produces = "application/json")
	@ApiOperation(value = "Get list of All Cinemas detaiils", response=MovieMgmtMovieVOResponseObject.class)
	public ResponseEntity<MovieMgmtCinemasVOResponseObject> chooseCinemas() throws Exception{
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & chooseCinemas() method to fetch list of Cinemas");	
		MovieMgmtCinemasVOResponseObject response = cinemasService.getAllCinemas();
		ResponseEntity<MovieMgmtCinemasVOResponseObject> responseObj = null;
		switch (response.getStatusCode()) {
		case HttpServletResponse.SC_NOT_FOUND:
			responseObj = new ResponseEntity<MovieMgmtCinemasVOResponseObject>(response, HttpStatus.NOT_FOUND);
			break;
		default:
			responseObj = new ResponseEntity<MovieMgmtCinemasVOResponseObject>(response, HttpStatus.OK);
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & chooseCinemas() method to fetch list of Cinemas");		
		return responseObj;
	}
	
	/**
	 * @param cinemasLocation
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path="/cinemas/loc", produces = "application/json")
	@ApiOperation(value = "Get list of Cinemas filtered by Cinemas Location", response=MovieMgmtMovieVOResponseObject.class)
	public ResponseEntity<MovieMgmtCinemasVOResponseObject> chooseCinemasByCinemasLocation(@RequestParam String cinemasLocation) throws Exception{
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & chooseCinemasByCinemasLocation() method to fetch list of Cinemas based on Location");	
		MovieMgmtCinemasVOResponseObject response = cinemasService.getAllCinemasByCinemasLocation(cinemasLocation);
		ResponseEntity<MovieMgmtCinemasVOResponseObject> responseObj = null;
		switch (response.getStatusCode()) {
		case HttpServletResponse.SC_NOT_FOUND:
			responseObj = new ResponseEntity<MovieMgmtCinemasVOResponseObject>(response, HttpStatus.NOT_FOUND);
			break;
		default:
			responseObj = new ResponseEntity<MovieMgmtCinemasVOResponseObject>(response, HttpStatus.OK);
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & chooseCinemasByCinemasLocation() method to fetch list of Cinemas based on Location");	
		return responseObj;
	}
	

	/**
	 * @param cinemasName
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path="/cinemas/list", produces = "application/json")
	@ApiOperation(value = "Get Cinemas details filtered by Cinemas Name", response=MovieMgmtMovieVOResponseObject.class)
	public ResponseEntity<MovieMgmtCinemasVOResponseObject> chooseCinemasByCinemasName(@RequestParam String cinemasName) throws Exception{
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & chooseCinemasByCinemasName() method to fetch list of Cinemas by Name");	
		ResponseEntity<MovieMgmtCinemasVOResponseObject> responseObj = null;
		MovieMgmtCinemasVOResponseObject response = cinemasService.getCinemasByCinemasName(cinemasName);
		switch (response.getStatusCode()) {
		case HttpServletResponse.SC_NOT_FOUND:
			responseObj = new ResponseEntity<MovieMgmtCinemasVOResponseObject>(response, HttpStatus.NOT_FOUND);
			break;
		default:
			responseObj = new ResponseEntity<MovieMgmtCinemasVOResponseObject>(response, HttpStatus.OK);
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+"class & chooseCinemasByCinemasName() method to fetch list of Cinemas by Name");	
		return responseObj;
	}
	

	/**
	 * @param cinemasId
	 * @return
	 * @throws MovieMgmtException 
	 * @throws Exception
	 */
	@GetMapping(path="/schedules/listbycinemasid", produces = "application/json")
	@ApiOperation(value = "Get Cinemas details filtered by Cinemas Id", response=MovieMgmtMovieVOResponseObject.class)
	public ResponseEntity<MovieMgmtMovieScheduleVOResponseObject> getScheduleByCinemas(@RequestParam Long cinemasId) throws MovieMgmtException{
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getScheduleByCinemas() to fetch list of Schedules by Id");	
		ResponseEntity<MovieMgmtMovieScheduleVOResponseObject> responseObj = null;
		MovieMgmtMovieScheduleVOResponseObject scheduleByCinemasIdResponse = scheduleService.getScheduleByCinemasId(cinemasId);
		switch (scheduleByCinemasIdResponse.getStatusCode()) {
		case HttpServletResponse.SC_NOT_FOUND:
			responseObj = new ResponseEntity<MovieMgmtMovieScheduleVOResponseObject>(scheduleByCinemasIdResponse, HttpStatus.NOT_FOUND);
			break;
		default:
			responseObj = new ResponseEntity<MovieMgmtMovieScheduleVOResponseObject>(scheduleByCinemasIdResponse, HttpStatus.OK);
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+"class & getScheduleByCinemas() to fetch list of Cinemas");	
		return responseObj;
	}
	

	/**
	 * @param cinemasName
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path="/schedules/listbycinemas", produces = "application/json")
	@ApiOperation(value = "Get Movie Schedule details filtered by Cinemas Name", response=MovieMgmtMovieVOResponseObject.class)
	public ResponseEntity<MovieMgmtMovieScheduleVOResponseObject> getScheduleByCinemasName(@RequestParam String cinemasName) throws MovieMgmtException{
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getScheduleByCinemasName() method to fetch list of Schedules by Id");	
		ResponseEntity<MovieMgmtMovieScheduleVOResponseObject> responseObj = null;
		MovieMgmtMovieScheduleVOResponseObject scheduleByCinemasResponse = scheduleService.getSchedulesByCinemasName(cinemasName);
		responseObj = new ResponseEntity<MovieMgmtMovieScheduleVOResponseObject>(scheduleByCinemasResponse, HttpStatus.OK);
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getScheduleByCinemasName() method to fetch list of Cinemas");	
		return responseObj;
	}
	

	/**
	 * @param movieName
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path="/schedules/listByName", produces = "application/json")
	@ApiOperation(value = "Get Movie Schedule details filtered by Movie Name", response=MovieMgmtMovieVOResponseObject.class)
	public ResponseEntity<MovieMgmtMovieScheduleVOResponseObject> getScheduleByMovieName(@RequestParam String movieName) throws Exception{
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class &  getScheduleByMovieName() method to fetch list of Schedules");	
		ResponseEntity<MovieMgmtMovieScheduleVOResponseObject> responseObj = null;
		MovieMgmtMovieScheduleVOResponseObject scheduleByMovieResponse = scheduleService.getScheduleByMovieName(movieName);
		switch (scheduleByMovieResponse.getStatusCode()) {
		case HttpServletResponse.SC_NOT_FOUND:
			responseObj = new ResponseEntity<MovieMgmtMovieScheduleVOResponseObject>(scheduleByMovieResponse, HttpStatus.NOT_FOUND);
			break;
		default:
			responseObj = new ResponseEntity<MovieMgmtMovieScheduleVOResponseObject>(scheduleByMovieResponse, HttpStatus.OK);
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class &  getScheduleByMovieName() method to fetch list of Schedules");	
		return responseObj;
	}
	
	/**
	 * @param movieName
	 * @param cinemasName
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path = "/schedules/list", produces = "application/json")
	@ApiOperation(value = "Get Movie Schedule details filtered by Cinemas Name and Movie Name", response = MovieMgmtMovieVOResponseObject.class)
	public ResponseEntity<MovieMgmtMovieScheduleVOResponseObject> getScheduleByCinemasNameAndMovieName(
			@RequestParam String movieName, @RequestParam String cinemasName) throws Exception {

		LOGGER.debug("Entering the " + getClass().getSimpleName()+" class & getScheduleByCinemasNameAndMovieName() method to fetch list of Schedules");
		ResponseEntity<MovieMgmtMovieScheduleVOResponseObject> responseObj = null;
		MovieMgmtMovieScheduleVOResponseObject scheduleByNamesResponse = scheduleService.getSchedules(movieName,
				cinemasName);
		switch (scheduleByNamesResponse.getStatusCode()) {
		case HttpServletResponse.SC_NOT_FOUND:
			responseObj = new ResponseEntity<MovieMgmtMovieScheduleVOResponseObject>(scheduleByNamesResponse,
					HttpStatus.NOT_FOUND);
			break;
		default:
			responseObj = new ResponseEntity<MovieMgmtMovieScheduleVOResponseObject>(scheduleByNamesResponse,
					HttpStatus.OK);
		}
		LOGGER.debug("Exiting the " + getClass().getSimpleName()+" class & getScheduleByCinemasNameAndMovieName() method to fetch list of Schedules");
		return responseObj;
	}

	/**
	 * @param movieId
	 * @param cinemasId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path = "/schedulesByIds/list", produces = "application/json")
	@ApiOperation(value = "Get Movie Schedule details filtered by Cinemas Id and Movie Id", response = MovieMgmtMovieVOResponseObject.class)
	public ResponseEntity<MovieMgmtMovieScheduleVOResponseObject> getScheduleByCinemasIdAndMovieId(
			@RequestParam Long movieId, @RequestParam Long cinemasId) throws Exception {
		
		LOGGER.debug("Entering the "+ getClass().getSimpleName()+" class & getScheduleByCinemasIdAndMovieId() method to fetch list of Schedules");
		ResponseEntity<MovieMgmtMovieScheduleVOResponseObject> responseObj = null;
		MovieMgmtMovieScheduleVOResponseObject scheduleByIdResponse = scheduleService.getSchedulesByIds(movieId,
				cinemasId);
		switch (scheduleByIdResponse.getStatusCode()) {
		case HttpServletResponse.SC_NOT_FOUND:
			responseObj = new ResponseEntity<MovieMgmtMovieScheduleVOResponseObject>(scheduleByIdResponse,
					HttpStatus.NOT_FOUND);
			break;
		default:
			responseObj = new ResponseEntity<MovieMgmtMovieScheduleVOResponseObject>(scheduleByIdResponse,
					HttpStatus.OK);
		}
		LOGGER.debug(
				"Exiting the " +getClass().getSimpleName()+ " class & getScheduleByCinemasIdAndMovieId() to fetch list of Schedules");
		return responseObj;
	}
	
	/**
	 * @param movie
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping(path = "/movie", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "Add newly released Movie entry", response=MovieMgmtMovieVOResponseObject.class)
	public ResponseEntity<MovieMgmtMovieVOResponseObject> addMovieEntry(@Valid @RequestBody MovieVO movie, HttpServletResponse response)
			throws Exception {
		
		LOGGER.debug("Entering the add Movie(POST) method of "+getClass().getSimpleName()+" class & addMovieEntry() method to add movie");
		ResponseEntity<MovieMgmtMovieVOResponseObject> responseObj = null;
		MovieMgmtMovieVOResponseObject addMovieResponse = movieService.addMovie(movie);
		switch (addMovieResponse.getStatusCode()) {
		case HttpServletResponse.SC_FOUND:
			responseObj = new ResponseEntity<MovieMgmtMovieVOResponseObject>(addMovieResponse, HttpStatus.BAD_REQUEST);
			break;
		default:
			URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/getMovieById/{id}")
					.buildAndExpand(addMovieResponse.getId()).toUri();
			response.setHeader("Location", location.toString());
			responseObj = new ResponseEntity<MovieMgmtMovieVOResponseObject>(addMovieResponse, HttpStatus.CREATED);
		}
		LOGGER.debug("Exiting the add Movie(POST) method of "+getClass().getSimpleName()+" class & addMovieEntry() method to add movie");
		return responseObj;
	}
	
	/**
	 * @param cinema
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping(path="/cinemas", consumes="application/json", produces="application/json")
	@ApiOperation(value = "Add a new Cinemas to the database", response=MovieMgmtMovieVOResponseObject.class)
	public ResponseEntity<MovieMgmtCinemasVOResponseObject> addCinemasEntry(@Valid @RequestBody CinemasVO cinema,
			HttpServletResponse response) {
		
		LOGGER.debug("Entering the add Cinemas(POST) method of "+getClass().getSimpleName()+" class to add cinemas");
		ResponseEntity<MovieMgmtCinemasVOResponseObject> responseObj = null;
		MovieMgmtCinemasVOResponseObject addCinemasResponse = cinemasService.addCinemas(cinema);
		switch (addCinemasResponse.getStatusCode()) {
		case HttpServletResponse.SC_FOUND:
			responseObj = new ResponseEntity<MovieMgmtCinemasVOResponseObject>(addCinemasResponse, HttpStatus.BAD_REQUEST);
			break;
		default:
			URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/getCinemasById/{id}")
					.buildAndExpand(addCinemasResponse.getId()).toUri();
			response.setHeader("Location", location.toString());
			responseObj = new ResponseEntity<MovieMgmtCinemasVOResponseObject>(addCinemasResponse, HttpStatus.CREATED);
		}
		LOGGER.debug("Exiting the add Cinemas(POST) method of "+getClass().getSimpleName()+" class to add cinemas");
		return responseObj;
	}
	
	/**
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping(path="/cinemas/{id}")
	@ApiOperation(value = "Delete Cinemas details by Cinemas Id", response=MovieMgmtMovieVOResponseObject.class)
	public void deleteCinemasEntity(@PathVariable Long id) throws Exception{
		
		LOGGER.debug("Delete Cinemas(DELETE) method of"+getClass().getSimpleName()+" class");
		cinemasService.deleteByCinemasId(id);
	}
	
	/**
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping(path="/movie/{id}")
	@ApiOperation(value = "Delete Movie details by Movie Id", response=MovieMgmtMovieVOResponseObject.class)
	public void deleteMovieEntity(@PathVariable Long id) throws Exception{
		
		LOGGER.debug("Delete Movie(DELETE) method of"+getClass().getSimpleName()+" class");
		movieService.deleteById(id);
	}
	
	/**
	 * @param movieId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path="/schedules/bymovieid", produces="application/json")
	@ApiOperation(value = "Get Movie Schedule details by Movie Id", response=MovieMgmtMovieVOResponseObject.class)
	public ResponseEntity<MovieMgmtMovieScheduleVOResponseObject> getScheduleByMovieId(@RequestParam Long movieId) throws Exception{
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getScheduleByMovieId() method to get schedules");
		ResponseEntity<MovieMgmtMovieScheduleVOResponseObject> responseObj = null;
		MovieMgmtMovieScheduleVOResponseObject getMovieResponse = scheduleService.getScheduleByMovieId(movieId);
		switch (getMovieResponse.getStatusCode()) {
		case HttpServletResponse.SC_NOT_FOUND:
			responseObj = new ResponseEntity<MovieMgmtMovieScheduleVOResponseObject>(getMovieResponse, HttpStatus.NOT_FOUND);
			break;
		default:
			responseObj = new ResponseEntity<MovieMgmtMovieScheduleVOResponseObject>(getMovieResponse, HttpStatus.OK);
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getScheduleByMovieId() method to get schedules");
		return responseObj;
	}
	
	/**
	 * @param cinemasId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path="/schedules/cinemaid", produces="application/json")
	@ApiOperation(value = "Get Movie Schedule details filtered by Schedule Id", response=MovieMgmtMovieVOResponseObject.class)
	public ResponseEntity<MovieMgmtMovieScheduleVOResponseObject> getScheduleByCinemasId(@RequestParam Long cinemasId) throws Exception{
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getScheduleByCinemasId() method to get schedules");
		ResponseEntity<MovieMgmtMovieScheduleVOResponseObject> responseObj = null;
		MovieMgmtMovieScheduleVOResponseObject getCinemasResponse = scheduleService.getScheduleByCinemasId(cinemasId);
		switch (getCinemasResponse.getStatusCode()) {
		case HttpServletResponse.SC_NOT_FOUND:
			responseObj = new ResponseEntity<MovieMgmtMovieScheduleVOResponseObject>(getCinemasResponse, HttpStatus.NOT_FOUND);
			break;
		default:
			responseObj = new ResponseEntity<MovieMgmtMovieScheduleVOResponseObject>(getCinemasResponse, HttpStatus.OK);
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getScheduleByCinemasId() method to get schedules");
		return responseObj;
	}
	
	@PostMapping(path="/schedules/showdetails", consumes="application/json")
	@ApiOperation(value = "Get Movie Schedule details filtered by Cinemas Id, Movie Id, Show Date and Timings", response=MovieMgmtMovieVOResponseObject.class)
	public long getSchdIdByMovieScheduleDetails(@RequestBody MovieScheduleRequestVO requestVO){
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+ "class getSchdIdByMovieScheduleDetails() method to get schedules");
		Long scheduleId = scheduleService.getSchdIdByMovieScheduleDetails(requestVO);
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class getSchdIdByMovieScheduleDetails() method to get schedules");	
		return scheduleId != 0 ? scheduleId : 0;
	}
	
	@PutMapping(path="/schedules/showdetails", consumes="application/json")
	@ApiOperation(value = "Update Movie Schedule details filtered by Cinemas Id, Movie Id, Show Date and Timings", response=MovieMgmtMovieVOResponseObject.class)
	public ResponseEntity<MovieMgmtMovieScheduleVOResponseObject> updateMovieSchedule(@RequestBody Map<String, Object> updateFields) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & getScheduleByCinemasId() method to update schedules");
		ResponseEntity<MovieMgmtMovieScheduleVOResponseObject> responseObj = null;
		MovieMgmtMovieScheduleVOResponseObject response = scheduleService.saveAndUpdate(updateFields);
		responseObj = new ResponseEntity<MovieMgmtMovieScheduleVOResponseObject>(response, HttpStatus.OK);
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & getScheduleByCinemasId() method to update schedules");	
		return responseObj;
	}
	
	@GetMapping(path="/getMovieName")
	public String getMovieName() {
		
		MovieMgmtMovieVOResponseObject response = movieService.getAllMovies();
		String name="ANand";
		if(null != response) {
			name= response.getResultList().get(0).getMovieTitle();
		}
		return name;
	}
	
}