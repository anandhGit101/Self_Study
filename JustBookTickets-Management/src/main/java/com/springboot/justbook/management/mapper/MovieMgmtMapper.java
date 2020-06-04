/**
 * 
 */
package com.springboot.justbook.management.mapper;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.justbook.management.domain.Cinemas;
import com.springboot.justbook.management.domain.Movie;
import com.springboot.justbook.management.domain.MovieSchedule;
import com.springboot.justbook.management.repository.CinemasRepository;
import com.springboot.justbook.management.repository.MovieRepository;
import com.springboot.justbook.vo.CinemasVO;
import com.springboot.justbook.vo.MovieScheduleVO;
import com.springboot.justbook.vo.MovieVO;

/**
 * @author M1006601
 *
 */
@Component
public class MovieMgmtMapper {
	
	/**
	 * LOGGER Logger object for logging in different logger Levels.
	 */
	static Logger LOGGER = LoggerFactory.getLogger(MovieMgmtMapper.class);
	
	private MovieMgmtMapper() {
	}
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	CinemasRepository cineRepository;
	
	public List<MovieVO> mapMoviesToMovieVOList(List<Movie> moviesList){
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & mapMoviesToMovieVOList() method");
		List<MovieVO> movieVOList = new ArrayList<MovieVO>();
		if(null!= moviesList) {
			for(Movie movie: moviesList) {
				movieVOList.add(mapMovieToMovieVO(movie));
			}
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & mapMoviesToMovieVOList() method");
		return movieVOList;
	}
	
	public List<MovieVO> mapMovieToMovieVOList(Movie fetchedMovie) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & mapMovieToMovieVOList() method");
		List<MovieVO> movieVOList = new ArrayList<MovieVO>();
		movieVOList.add(mapMovieToMovieVO(fetchedMovie));
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & mapMovieToMovieVOList() method");
		return movieVOList;
	}
	
	private MovieVO mapMovieToMovieVO(Movie movie) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & mapMovieToMovieVO() method");
		MovieVO movVO = new MovieVO();
		movVO.setMovieTitle(movie.getMovieTitle());
		movVO.setMovieCast(movie.getMovieCast());
		movVO.setMovieSynopsis(movie.getMovieSynopsis());
		movVO.setMovieLanguage(movie.getMovieLanguage());
		movVO.setMovieGenre(movie.getMovieGenre());
		movVO.setMovieReleaseDate(movie.getMovieReleaseDate());
		movVO.setMovieId(movie.getMovieId());
		movVO.setImageURL(movie.getMovieImgURL());
		movVO.setActive(movie.isMovieActive());
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & mapMovieToMovieVO() method");
		return movVO;
	}

	public List<CinemasVO> mapCinemasToCinemaVOList(List<Cinemas> cinemasList) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & mapCinemasToCinemaVOList() method");
		List<CinemasVO> cinemasVOList = new ArrayList<CinemasVO>();
		if(null!= cinemasList) {
			for(Cinemas cinema: cinemasList) {
				cinemasVOList.add(mapCinemasToCinemasVO(cinema));
			}
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & mapCinemasToCinemaVOList() method");
		return cinemasVOList;
	}

	private CinemasVO mapCinemasToCinemasVO(Cinemas cinema) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & mapCinemasToCinemasVO() method");
		CinemasVO cineVO = new CinemasVO();
		cineVO.setCinemasId(cinema.getCinemasId());
		cineVO.setCinemasName(cinema.getCinemasName());
		cineVO.setCinemasLocation(cinema.getCinemasLocation());
		cineVO.setCinemasAddress(cinema.getCinemasAddress());
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & mapCinemasToCinemasVO() method");
		return cineVO;
	}
	
	public List<CinemasVO> mapCinemasToCinemaVOList(Cinemas cinemas) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & mapCinemasToCinemaVOList() method");
		List<CinemasVO> cinemasVOList = new ArrayList<CinemasVO>();
		if(null!= cinemas) {
			cinemasVOList.add(mapCinemasToCinemasVO(cinemas));
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & mapCinemasToCinemaVOList() method");
		return cinemasVOList;
	}

	public Map<String, MovieScheduleVO> mapCinemasAndMovie(Cinemas cine, Movie mov, MovieSchedule schedule, Map<String, MovieScheduleVO> movSchedMap) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & mapCinemasAndMovie() method");
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String key = schedule.getMovie().getMovieTitle().concat("~").concat(schedule.getCinemas().getCinemasName())
				.concat("~").concat(schedule.getShowDate().format(formatters));
		if( movSchedMap.containsKey(key)) {
			 List<LocalTime> timingsList = movSchedMap.get(key).getShowTimingsList();
			 timingsList.add(schedule.getShowTimings());
			 getSortedList(timingsList);
		}else {
			MovieScheduleVO movSchdVO = new MovieScheduleVO();
			movSchdVO.setMovieId(mov.getMovieId());
			movSchdVO.setMovieTitle(mov.getMovieTitle());
			movSchdVO.setMovieCast(mov.getMovieCast());
			movSchdVO.setMovieGenre(mov.getMovieGenre());
			movSchdVO.setMovieLanguage(mov.getMovieLanguage());
			movSchdVO.setMovieReleaseDate(mov.getMovieReleaseDate());
			movSchdVO.setCinemasId(cine.getCinemasId());
			movSchdVO.setCinemasName(cine.getCinemasName());
			movSchdVO.setCinemasAddress(cine.getCinemasAddress());
			movSchdVO.setCinemasLocation(cine.getCinemasLocation());
			 List<LocalTime> timingsList = new ArrayList<LocalTime>();
			timingsList.add(schedule.getShowTimings());
			getSortedList(timingsList);
			movSchdVO.setShowTimingsList(timingsList);
			movSchdVO.setShowDate(schedule.getShowDate());
			movSchedMap.put(key, movSchdVO);
		}
		
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & mapCinemasAndMovie() method");
		return movSchedMap;
		
	}

	private void getSortedList(List<LocalTime> timingsList) {
		Collections.sort(timingsList);
	}

	public Map<String, MovieScheduleVO> mapSchedulesListToSchedulesVOList(List<MovieSchedule> schedulesList) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & mapSchedulesListToSchedulesVOList() method");
		Map<String, MovieScheduleVO> movSchdVoMap = new HashMap<String, MovieScheduleVO>();
		for(MovieSchedule schedule: schedulesList) {
			Movie mov = schedule.getMovie();
			Cinemas cine = schedule.getCinemas();
			movSchdVoMap = mapCinemasAndMovie(cine, mov, schedule, movSchdVoMap);
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & mapSchedulesListToSchedulesVOList() method");
		return movSchdVoMap;
	}

	public Map<String, MovieScheduleVO> mapScheduleToSchedulesVOList(MovieSchedule schedules) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & mapScheduleToSchedulesVOList() method");
		Map<String, MovieScheduleVO> movSchdVoMap = new HashMap<String, MovieScheduleVO>();
		Long movieId=schedules.getMovie().getMovieId();
		Long cinemasId=schedules.getCinemas().getCinemasId();
		Movie mov = movieRepository.findMovieByMovieIdAndIsMovieActiveTrue(movieId);
		Cinemas cine = cineRepository.findCinemasByCinemasId(cinemasId);
		movSchdVoMap= mapCinemasAndMovie(cine, mov, schedules, movSchdVoMap);
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & mapScheduleToSchedulesVOList() method");
		return movSchdVoMap;		
	}

	public Movie mapMovieVOToMovie(MovieVO movie) {
		
		LOGGER.debug("Entering the "+getClass().getSimpleName()+" class & mapMovieVOToMovie() method");
		Movie mov= new Movie();
		mov.setMovieTitle(movie.getMovieTitle());
		mov.setMovieCast(movie.getMovieCast());
		mov.setMovieSynopsis(movie.getMovieSynopsis());
		mov.setMovieLanguage(movie.getMovieLanguage());
		mov.setMovieGenre(movie.getMovieGenre());
		mov.setMovieReleaseDate(movie.getMovieReleaseDate());
		mov.setMovieId(movie.getMovieId());
		mov.setMovieImgURL(movie.getImageURL());
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & mapMovieVOToMovie() method");
		return mov;
	}

	public Cinemas mapCinemasVOToCinema(@Valid CinemasVO cinema) {
		
		Cinemas cine = new Cinemas();
		cine.setCinemasId(cinema.getCinemasId());
		cine.setCinemasName(cinema.getCinemasName());
		cine.setCinemasLocation(cinema.getCinemasLocation());
		cine.setCinemasAddress(cinema.getCinemasAddress());
		return cine;
	}

	public Map<String, MovieScheduleVO> mapSchedulesListToSchedulesVOMap(List<MovieSchedule> movieScheduleList) {

		LOGGER.debug(
				"Entering the " + getClass().getSimpleName() + " class & mapSchedulesListToSchedulesVOMap() method");
		Map<String, MovieScheduleVO> movSchdVoMap = new HashMap<String, MovieScheduleVO>();
		for (MovieSchedule schedule : movieScheduleList) {
			Movie mov = schedule.getMovie();
			Cinemas cine = schedule.getCinemas();
			movSchdVoMap= mapCinemasAndMovie(cine, mov, schedule, movSchdVoMap);
		}
		LOGGER.debug("Exiting the "+getClass().getSimpleName()+" class & mapSchedulesListToSchedulesVOList() method");
		return movSchdVoMap;
	}

}
