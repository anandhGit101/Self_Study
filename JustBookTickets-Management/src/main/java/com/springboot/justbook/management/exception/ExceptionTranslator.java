/**
 * 
 */
package com.springboot.justbook.management.exception;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.springboot.justbook.vo.MovieMgmtMovieScheduleVOResponseObject;

/**
 * @author M1006601
 *
 */
@RestControllerAdvice
public class ExceptionTranslator {

	static Logger LOGGER = LoggerFactory.getLogger(ExceptionTranslator.class);

	@ExceptionHandler(MovieMgmtException.class)
	public MovieMgmtMovieScheduleVOResponseObject handleRequestsMovieMgmt(MovieMgmtException exp, WebRequest req) {

		LOGGER.error("Exception occured!: ", exp);
		String errorMsg = exp.getMessage();
		String[] errorDetails = errorMsg.split("~");
		MovieMgmtMovieScheduleVOResponseObject responseObj = new MovieMgmtMovieScheduleVOResponseObject();
		responseObj.setStatusCode(Integer.parseInt(errorDetails[0]));
		responseObj.setErrorCode(errorDetails[1]);
		responseObj.setDescription(errorDetails[2]);
		return responseObj;
	}

	/*
	 * @ExceptionHandler(value = { SeatsMgmtException.class })
	 * 
	 * @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) public
	 * MovieMgmtMovieScheduleVOResponseObject
	 * handleUnknownExceptions(SeatsMgmtException exception, WebRequest req) {
	 * 
	 * LOGGER.error("Exception occured!: ", exception);
	 * MovieMgmtMovieScheduleVOResponseObject responseObj = new
	 * MovieMgmtMovieScheduleVOResponseObject();
	 * responseObj.setErrorCode("Internal Server Error");
	 * responseObj.setDescription("Internal Server Error occured!");
	 * responseObj.setStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	 * return responseObj; }
	 */

	@ExceptionHandler(value = { SeatsMgmtException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public MovieMgmtMovieScheduleVOResponseObject handleNotFound(Exception exception, WebRequest req) {

		LOGGER.error("Exception occured!: ", exception);
		String errorMsg = exception.getMessage();
		String[] errorDetails = errorMsg.split("~");
		MovieMgmtMovieScheduleVOResponseObject responseObj = new MovieMgmtMovieScheduleVOResponseObject();
		responseObj.setStatusCode(Integer.parseInt(errorDetails[0]));
		responseObj.setErrorCode(errorDetails[1]);
		responseObj.setDescription(errorDetails[2]);
		return responseObj;
	}

	@ExceptionHandler(value = { NoHandlerFoundException.class, EmptyResultDataAccessException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public com.springboot.justbook.vo.MovieMgmtMovieScheduleVOResponseObject handleUnknownURI(Exception exception,
			WebRequest req) {

		LOGGER.error("Exception occured!: ", exception);
		MovieMgmtMovieScheduleVOResponseObject responseObj = new MovieMgmtMovieScheduleVOResponseObject();
		responseObj.setErrorCode("Not Found");
		responseObj.setDescription(exception.getMessage());
		responseObj.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
		return responseObj;
	}

	@ExceptionHandler(value = { MissingServletRequestParameterException.class, IllegalArgumentException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public MovieMgmtMovieScheduleVOResponseObject handleMissingParametersException(Exception exp, WebRequest req) {

		LOGGER.error("Exception occured!: ", exp);
		MovieMgmtMovieScheduleVOResponseObject responseObj = new MovieMgmtMovieScheduleVOResponseObject();
		responseObj.setErrorCode("Bad Request");
		responseObj.setDescription(exp.getMessage());
		responseObj.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		return responseObj;
	}
}
