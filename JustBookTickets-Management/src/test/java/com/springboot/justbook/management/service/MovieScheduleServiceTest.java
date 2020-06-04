/**
 * 
 */
package com.springboot.justbook.management.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.justbook.management.domain.Cinemas;
import com.springboot.justbook.management.domain.Movie;
import com.springboot.justbook.management.domain.MovieSchedule;
import com.springboot.justbook.management.mapper.MovieMgmtMapper;
import com.springboot.justbook.management.repository.CinemasRepository;
import com.springboot.justbook.management.repository.MovieRepository;
import com.springboot.justbook.management.repository.MovieScheduleRepository;
import com.springboot.justbook.management.service.impl.MovieScheduleServiceImpl;
import com.springboot.justbook.vo.MovieMgmtMovieScheduleVOResponseObject;
import com.springboot.justbook.vo.MovieScheduleVO;

/**
 * @author M1006601
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieScheduleServiceTest {
	
	@InjectMocks
	MovieScheduleServiceImpl movieScheduleService;
	
	@MockBean
	MovieScheduleRepository scheduleRepository;
	
	@MockBean
	MovieRepository movieRespository;
	
	@MockBean
	CinemasRepository cinemasRepository;
	
	@Autowired
	MovieMgmtMapper mapper;
	
	@Test
	public void getScheduleByCinemasId() {
		
		List<MovieSchedule> schedulesList = getMovieScheduleList();
		MovieMgmtMovieScheduleVOResponseObject response = new MovieMgmtMovieScheduleVOResponseObject();
		Map<String, MovieScheduleVO> mapSchedulesListToSchedulesVOList = mapper.mapSchedulesListToSchedulesVOList(schedulesList);
		response.setResultList(mapSchedulesListToSchedulesVOList);
		when(scheduleRepository.getMovieScheduleByMovieId(new Long(2))).thenReturn(schedulesList);
		assertNotNull(response);
		assertNotNull(response.getResultList());
		assertEquals(1, response.getResultList().size());
	}
	
	@Test
	public void getScheduleByMovieName() {
		
		Movie movie = getMovieScheduleListByName();
		MovieMgmtMovieScheduleVOResponseObject response = new MovieMgmtMovieScheduleVOResponseObject();
		response.setResultList(mapper.mapSchedulesListToSchedulesVOMap(movie.getMovieSchedule()));
		when(movieRespository.findMovieByMovieTitleAndIsMovieActiveTrue("Billa")).thenReturn(movie);
		assertNotNull(response);
		assertNotNull(response.getResultList());
	}

	@Test
	public void getSchedules() {

		Movie movie = getMovie();
		Cinemas cinemas = getCinemas();
		MovieMgmtMovieScheduleVOResponseObject response = new MovieMgmtMovieScheduleVOResponseObject();
		Map<String, MovieScheduleVO> mapSchedulesListToSchedulesVOList = mapper.mapSchedulesListToSchedulesVOList(getMovieScheduleList());
		response.setResultList(mapSchedulesListToSchedulesVOList);
		when(cinemasRepository.findCinemasByCinemasName("Vetri")).thenReturn(cinemas);
		when(movieRespository.findMovieByMovieTitleAndIsMovieActiveTrue("Billa")).thenReturn(movie);
		when(scheduleRepository.getMovieScheduleByMovieIdAndCinemasId(movie.getMovieId(), cinemas.getCinemasId()))
				.thenReturn(getMovieScheduleList());
		assertNotNull(response);
		assertNotNull(response.getResultList());
	}
	
	@Test
	public void getSchedulesByIds() {
		MovieMgmtMovieScheduleVOResponseObject response = new MovieMgmtMovieScheduleVOResponseObject();
		response.setResultList(mapper.mapSchedulesListToSchedulesVOList(getMovieScheduleList()));
		when(scheduleRepository.getMovieScheduleByMovieIdAndCinemasId(new Long(2), new Long(1))).thenReturn(getMovieScheduleList());
		assertNotNull(response);
		assertNotNull(response.getResultList());
	}
	
	@Test
	public void getSchedulesByCinemasName() {
		
		Cinemas cinema = getCinemas();
		cinema.setMovieSchedule(getScheduleList());
		MovieMgmtMovieScheduleVOResponseObject response = new MovieMgmtMovieScheduleVOResponseObject();
		response.setResultList(mapper.mapSchedulesListToSchedulesVOList(cinema.getMovieSchedule()));
		when(cinemasRepository.findCinemasByCinemasName("Vetri")).thenReturn(cinema);
		assertNotNull(response);
		assertNotNull(response.getResultList());
	}
	
	@Test
	public void getSchdIdByMovieScheduleDetails() {
	
		MovieSchedule movSchedule= new MovieSchedule(Long.valueOf(2),
				new Movie(Long.valueOf(2), "Billa", "Ajithkumar, Nayanthara, Prabhu",
						"Billa is a 2007 Indian Tamil-language action thriller film directed by Vishnuvardhan.",
						"Tamil", LocalDate.parse("2007-08-15", DateTimeFormatter.ISO_DATE), "Action-Thriller",null,true),
				new Cinemas(Long.valueOf(1), "Vetri", "Vetri Theatre Complex, Chromepet", "Chennai"),
				LocalTime.parse("14:15"), LocalDate.parse("2019-09-14", DateTimeFormatter.ISO_DATE), 60);
		when(scheduleRepository.findScheduleIdByIdsAndTimings(new Long(2), new Long(1), 
				LocalDate.parse("2020-01-14", DateTimeFormatter.ISO_DATE), LocalTime.parse("11:15")))
		.thenReturn(movSchedule);
		assertNotNull(movSchedule);
		assertEquals(new Long(2), movSchedule.getId());
	}
	
	private Movie getMovie() {
		return new Movie(Long.valueOf(2), "Billa", "Ajithkumar, Nayanthara, Prabhu",
				"Billa is a 2007 Indian Tamil-language action thriller film directed by Vishnuvardhan.",
				"Tamil", LocalDate.parse("2007-08-15", DateTimeFormatter.ISO_DATE), "Action-Thriller",null,true);
	}
	
	private Cinemas getCinemas() {
		return new Cinemas(Long.valueOf(1), "Vetri", "Vetri-Rakesh Theatre Complex, Chromepet", "Chennai");
	}
	private Movie getMovieScheduleListByName() {

		Movie movie = getMovie();
		movie.setMovieSchedule(getScheduleList());
		return movie;	
	}
	
	private List<MovieSchedule> getScheduleList() {
		List<MovieSchedule> scheduleList = new ArrayList<>();
		MovieSchedule movSched = new MovieSchedule(Long.valueOf(1), getMovie(), getCinemas(),
				LocalTime.parse("11:15"), LocalDate.parse("2020-01-14", DateTimeFormatter.ISO_DATE), 60);
		scheduleList.add(movSched);
		movSched = new MovieSchedule(Long.valueOf(2), getMovie(), getCinemas(), 
				LocalTime.parse("14:15"), LocalDate.parse("2020-01-14", DateTimeFormatter.ISO_DATE), 60);
		scheduleList.add(movSched);
		return scheduleList;
	}

	private List<MovieSchedule> getMovieScheduleList() {
		
		List<MovieSchedule> scheduleList = new ArrayList<>();
		MovieSchedule movSched = new MovieSchedule(Long.valueOf(2),
				getMovie(), getCinemas(),LocalTime.parse("14:15"), LocalDate.parse("2019-09-14", DateTimeFormatter.ISO_DATE), 60);
		scheduleList.add(movSched);
		return scheduleList;	
	}

}
