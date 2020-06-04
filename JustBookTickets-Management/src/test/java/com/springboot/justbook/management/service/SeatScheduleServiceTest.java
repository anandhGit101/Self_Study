/**
 * 
 */
package com.springboot.justbook.management.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
import com.springboot.justbook.management.domain.SeatSchedule;
import com.springboot.justbook.management.mapper.SeatScheduleMapper;
import com.springboot.justbook.management.repository.MovieScheduleRepository;
import com.springboot.justbook.management.repository.SeatScheduleRepository;
import com.springboot.justbook.management.service.impl.SeatScheduleServiceImpl;
import com.springboot.justbook.vo.MovieMgmtSeatScheduleVOResponseObject;

/**
 * @author M1006601
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SeatScheduleServiceTest {
	
	@InjectMocks
	SeatScheduleServiceImpl seatsScheduleService;

	@MockBean
	SeatScheduleRepository seatScheduleRepository;
	
	@MockBean
	MovieScheduleRepository movieSchdRepository;
	
	@Autowired
	SeatScheduleMapper scheduleMapper;	
	
	@Test
	public void findSeatScheduleByMovieScheduleId() {
		
		SeatSchedule seatSchd=getSeatSchedule();
		MovieMgmtSeatScheduleVOResponseObject response = new MovieMgmtSeatScheduleVOResponseObject();
		response.setResultList(scheduleMapper.mapSeatScheduleToList(seatSchd));
		when(seatScheduleRepository.findSeatScheduleByScheduleId(new Long(1))).thenReturn(seatSchd);
		assertNotNull(response);
		assertNotNull(response.getResultList());
	}

	private SeatSchedule getSeatSchedule() {
		
		Movie movie = new Movie(Long.valueOf(1), "Billa 2", "Ajithkumar, Parvathy Omanakuttan, Bruna, Ilavarasu",
				"Billa 2 is a 2012 Indian Tamil-language action thriller film directed by Chakri Toleti.",
				"Tamil", LocalDate.parse("2012-07-13", DateTimeFormatter.ISO_DATE), "Action-Thriller",null,true);
		Cinemas cinemas = new Cinemas(Long.valueOf(1), "Vetri", "Vetri Theatre Complex, Chromepet", "Chennai");
		MovieSchedule movieschedule = new MovieSchedule(Long.valueOf(1), movie, cinemas	,
				LocalTime.parse("11:15"), LocalDate.parse("2019-09-14", DateTimeFormatter.ISO_DATE), 58);
		SeatSchedule seatSchedule = new SeatSchedule(new Long(1), movieschedule, "A1, A2");
		return seatSchedule;
	}
}
