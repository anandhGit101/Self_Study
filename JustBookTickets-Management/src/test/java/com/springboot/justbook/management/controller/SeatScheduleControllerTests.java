/**
 * 
 */
package com.springboot.justbook.management.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.justbook.management.domain.Cinemas;
import com.springboot.justbook.management.domain.Movie;
import com.springboot.justbook.management.domain.MovieSchedule;
import com.springboot.justbook.management.domain.SeatSchedule;
import com.springboot.justbook.management.exception.SeatsMgmtException;
import com.springboot.justbook.management.mapper.SeatScheduleMapper;
import com.springboot.justbook.management.repository.MovieScheduleRepository;
import com.springboot.justbook.management.repository.SeatScheduleRepository;
import com.springboot.justbook.management.repository.SeatsRepository;
import com.springboot.justbook.management.service.SeatScheduleService;
import com.springboot.justbook.vo.MovieMgmtSeatScheduleVOResponseObject;
import com.springboot.justbook.vo.SeatScheduleVO;

/**
 * @author M1006601
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureWebMvc
public class SeatScheduleControllerTests {
	
	
	@InjectMocks
	SeatScheduleController seatScheduleController;
		
	@Autowired
	private SeatScheduleService seatSchService;
	
	@Mock
	private SeatScheduleRepository seatScheduleRepository;
	
	@Mock
	private SeatsRepository SeatsRepository;
	
	@Mock
	private MovieScheduleRepository movieSchdRepository;
	
	@Mock
	private HttpServletResponse response;
	
	@Mock
	private SeatScheduleMapper scheduleMapper;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		ReflectionTestUtils.setField(seatScheduleController, "seatSchService", seatSchService);
		ReflectionTestUtils.setField(seatSchService, "seatScheduleRepository", seatScheduleRepository);
		ReflectionTestUtils.setField(seatSchService, "scheduleMapper", scheduleMapper);
		ReflectionTestUtils.setField(scheduleMapper, "movieSchdRepository", movieSchdRepository);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.springboot.justbook.management.controller.SeatScheduleController#getSeatsBookedByScheduleId(java.lang.Long)}.
	 * @throws SeatsMgmtException 
	 */
	@Test
	public void testGetSeatsBookedByScheduleId() throws SeatsMgmtException {

		SeatSchedule schedule =new SeatSchedule(Long.valueOf(1), new MovieSchedule(Long.valueOf(1),
				new Movie(Long.valueOf(1), "Billa 2", "Ajithkumar, Parvathy Omanakuttan, Bruna, Ilavarasu",
						"Billa 2 is a 2012 Indian Tamil-language action thriller film directed by Chakri Toleti.",
						"Tamil", LocalDate.parse("2012-07-13", DateTimeFormatter.ISO_DATE), "Action-Thriller",null,true),
				new Cinemas(Long.valueOf(1), "Vetri", "Vetri Theatre Complex, Chromepet", "Chennai"),
				LocalTime.parse("11:15"), LocalDate.parse("2019-09-14", DateTimeFormatter.ISO_DATE), 60),
				new String("A1, A2, A3, A4"));
		Mockito.when(seatScheduleRepository.findSeatScheduleByMovieScheduleId(ArgumentMatchers.anyLong()))
				.thenReturn(schedule);
		ResponseEntity<MovieMgmtSeatScheduleVOResponseObject> seatsBookedByScheduleId = seatScheduleController
				.getSeatsBookedByScheduleId(Long.valueOf(1));
		assertNotNull(seatsBookedByScheduleId);
		assertThat(seatsBookedByScheduleId.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	/**
	 * Test method for {@link com.springboot.justbook.management.controller.SeatScheduleController#addSeatsSchedule(com.springboot.justbook.management.vo.SeatScheduleVO)}.
	 * @throws Exception 
	 * @throws JsonProcessingException 
	 */
	@Test
	public void testAddSeatsSchedule() throws JsonProcessingException, Exception {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		SeatSchedule seatSchedule = new SeatSchedule();
		seatSchedule.setId(Long.valueOf(1));
		seatSchedule.setSchedule(new MovieSchedule(Long.valueOf(1),
				new Movie(Long.valueOf(1), "Billa 2", "Ajithkumar, Parvathy Omanakuttan, Bruna, Ilavarasu",
						"Billa 2 is a 2012 Indian Tamil-language action thriller film directed by Chakri Toleti.",
						"Tamil", LocalDate.parse("2012-07-13", DateTimeFormatter.ISO_DATE), "Action-Thriller",null,true),
				new Cinemas(Long.valueOf(1), "Vetri", "Vetri Theatre Complex, Chromepet", "Chennai"),
				LocalTime.parse("11:15"), LocalDate.parse("2019-09-14", DateTimeFormatter.ISO_DATE), 60));
		seatSchedule.setSeatsOccupied(new String("A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13"));
		
		SeatScheduleVO seatScheduleVO = new SeatScheduleVO();
		seatScheduleVO.setId(Long.valueOf(1));
		seatScheduleVO.setScheduleId(Long.valueOf(1));
		seatScheduleVO.setSeatsOccupied(new String("B1, B2, B3, B4, B5"));
		
		/*MovieSchedule movieSchd=new MovieSchedule(Long.valueOf(1),
				new Movie(Long.valueOf(1), "Billa 2", "Ajithkumar, Parvathy Omanakuttan, Bruna, Ilavarasu",
						"Billa 2 is a 2012 Indian Tamil-language action thriller film directed by Chakri Toleti.",
						"Tamil", LocalDate.parse("2012-07-13", DateTimeFormatter.ISO_DATE), "Action-Thriller"),
				new Cinemas(Long.valueOf(1), "Vetri", "Vetri Theatre Complex, Chromepet", "Chennai"),
				LocalTime.parse("11:15"), LocalDate.parse("2019-09-14", DateTimeFormatter.ISO_DATE));*/
		
		Mockito.when(seatScheduleRepository.findSeatScheduleByScheduleId(Mockito.anyLong())).thenReturn(seatSchedule);
		Mockito.when(scheduleMapper.mapVOToScheduleEntity(seatScheduleVO)).thenReturn(seatSchedule);
		ResponseEntity<MovieMgmtSeatScheduleVOResponseObject> addSeatsSchedule = seatScheduleController.addSeatsSchedule(seatScheduleVO);
		//Mockito.when(seatScheduleRepository.save(ArgumentMatchers.any())).thenReturn(seatSchedule);
		assertNotNull(addSeatsSchedule);
		
	}
}
