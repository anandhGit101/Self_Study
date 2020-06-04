/**
 * 
 */
package com.springboot.justbook.searchservice.controller;

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

import com.springboot.justbook.searchservice.service.CinemasService;
import com.springboot.justbook.searchservice.vo.CinemasVO;
import com.springboot.justbook.searchservice.vo.JBTResponseObject;

/**
 * @author M1006601
 *
 */
@RestController
@RequestMapping("/jbt")
public class JBTELKCinemasController {
	
	@Autowired
	CinemasService cinemasService;
	
	@Autowired
    Client client;
	
	@GetMapping(path="/cinemas", produces = "application/json")
	public ResponseEntity<JBTResponseObject> getAllCinemas() throws Exception{
		
		ResponseEntity<JBTResponseObject> responseObj = null;
		JBTResponseObject cinemasELKResObj = cinemasService.getAllCinemas();
		switch (cinemasELKResObj.getStatusCode()) {
		case HttpServletResponse.SC_NOT_FOUND:
			responseObj = new ResponseEntity<JBTResponseObject>(cinemasELKResObj, HttpStatus.NOT_FOUND);
			break;
		default:
			responseObj = new ResponseEntity<JBTResponseObject>(cinemasELKResObj, HttpStatus.OK);
		}
		return responseObj;		
	}
	
	@GetMapping(path="/cinemas/loc", produces = "application/json")
	public ResponseEntity<JBTResponseObject> getCinemasByLocation(@RequestParam String cinemasLocation) throws Exception{
		
		ResponseEntity<JBTResponseObject> responseObj = null;
		JBTResponseObject cinemasELKResObj = cinemasService.getCinemasByLocation(cinemasLocation);
		switch (cinemasELKResObj.getStatusCode()) {
		case HttpServletResponse.SC_NOT_FOUND:
			responseObj = new ResponseEntity<JBTResponseObject>(cinemasELKResObj, HttpStatus.NOT_FOUND);
			break;
		default:
			responseObj = new ResponseEntity<JBTResponseObject>(cinemasELKResObj, HttpStatus.OK);
		}
		return responseObj;
	}

	@GetMapping(path="/cinemas/name", produces = "application/json")
	public ResponseEntity<JBTResponseObject> getCinemasByName(@RequestParam String cinemasName) throws Exception{
		
		ResponseEntity<JBTResponseObject> responseObj = null;
		JBTResponseObject cinemasELKResObj = cinemasService.findCinemasByCinemasName(cinemasName);
		switch (cinemasELKResObj.getStatusCode()) {
		case HttpServletResponse.SC_NOT_FOUND:
			responseObj = new ResponseEntity<JBTResponseObject>(cinemasELKResObj, HttpStatus.NOT_FOUND);
			break;
		default:
			responseObj = new ResponseEntity<JBTResponseObject>(cinemasELKResObj, HttpStatus.OK);
		}
		return responseObj;
	}
	@PostMapping(path="/cinemas", consumes="application/json", produces="application/json")
	public ResponseEntity<JBTResponseObject> addCinemas(@Valid @RequestBody CinemasVO cinema,
			HttpServletResponse response){
		
		ResponseEntity<JBTResponseObject> responseObj = null;
		JBTResponseObject respObj = cinemasService.addCinemas(cinema);
		switch (respObj.getStatusCode()) {
		case HttpServletResponse.SC_FOUND:
			responseObj = new ResponseEntity<JBTResponseObject>(respObj, HttpStatus.BAD_REQUEST);
			break;
		default:
			responseObj = new ResponseEntity<JBTResponseObject>(respObj, HttpStatus.CREATED);
		}
		return responseObj;
		}
}
