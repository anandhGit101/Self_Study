/**
 * 
 */
package com.springboot.justbook.management.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.springboot.justbook.management.domain.Seats;
import com.springboot.justbook.management.exception.SeatsMgmtException;
import com.springboot.justbook.management.repository.SeatsRepository;
import com.springboot.justbook.management.service.SeatsService;
import com.springboot.justbook.vo.MovieMgmtSeatsVOResponseObject;

/**
 * @author M1006601
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureWebMvc
public class SeatsControllerTests {

	@InjectMocks
	private SeatsController seatsController;
	
	@Autowired
	private SeatsService seatsService;
	
	@Mock
	private SeatsRepository seatsRepository;
	
	private List<Seats> seatsList;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() {
		ReflectionTestUtils.setField(seatsController, "seatsService", seatsService);
		ReflectionTestUtils.setField(seatsService, "seatsRepository", seatsRepository);
		seatsList = Arrays.asList(
				new Seats(Long.valueOf(1), "A1", "First Class", new BigDecimal(183.65)),
				new Seats(Long.valueOf(2), "A2", "First Class", new BigDecimal(183.65)),
				new Seats(Long.valueOf(3), "A3", "First Class", new BigDecimal(183.65)),
				new Seats(Long.valueOf(4), "A4", "First Class", new BigDecimal(183.65)),
				new Seats(Long.valueOf(5), "A5", "First Class", new BigDecimal(183.65)),
				new Seats(Long.valueOf(6), "A6", "First Class", new BigDecimal(183.65)),
				new Seats(Long.valueOf(7), "A7", "First Class", new BigDecimal(183.65)),
				new Seats(Long.valueOf(8), "A8", "First Class", new BigDecimal(183.65)),
				new Seats(Long.valueOf(9), "A9", "First Class", new BigDecimal(183.65)),
				new Seats(Long.valueOf(10), "A10", "First Class", new BigDecimal(183.65)),
				new Seats(Long.valueOf(11), "A11", "First Class", new BigDecimal(183.65)),
				new Seats(Long.valueOf(12), "A12", "First Class", new BigDecimal(183.65)),
				new Seats(Long.valueOf(13), "A13", "First Class", new BigDecimal(183.65)),
				new Seats(Long.valueOf(14), "B1", "First Class", new BigDecimal(183.65)),
				new Seats(Long.valueOf(15), "B2", "First Class", new BigDecimal(183.65)),
				new Seats(Long.valueOf(16), "B3", "First Class", new BigDecimal(183.65)),
				new Seats(Long.valueOf(17), "B4", "First Class", new BigDecimal(183.65)),
				new Seats(Long.valueOf(18), "B5", "First Class", new BigDecimal(183.65)),
				new Seats(Long.valueOf(19), "B6", "First Class", new BigDecimal(183.65)),
				new Seats(Long.valueOf(20), "B7", "First Class", new BigDecimal(183.65)),
				new Seats(Long.valueOf(21), "B8", "First Class", new BigDecimal(183.65)),
				new Seats(Long.valueOf(22), "B9", "First Class", new BigDecimal(183.65)),
				new Seats(Long.valueOf(23), "B10", "First Class", new BigDecimal(183.65)),
				new Seats(Long.valueOf(24), "C1", "Budget Class",  new BigDecimal(75.65)),
				new Seats(Long.valueOf(25), "C2", "Budget Class",  new BigDecimal(75.65)),
				new Seats(Long.valueOf(26), "C3", "Budget Class",  new BigDecimal(75.65)),
				new Seats(Long.valueOf(27), "C4", "Budget Class",  new BigDecimal(75.65)),
				new Seats(Long.valueOf(28), "C5", "Budget Class",  new BigDecimal(75.65)),
				new Seats(Long.valueOf(29), "C6", "Budget Class",  new BigDecimal(75.65)),
				new Seats(Long.valueOf(30), "C7", "Budget Class",  new BigDecimal(75.65)),
				new Seats(Long.valueOf(31), "C8", "Budget Class",  new BigDecimal(75.65)),
				new Seats(Long.valueOf(32), "C9", "Budget Class",  new BigDecimal(75.65)),
				new Seats(Long.valueOf(33), "C10", "Budget Class",  new BigDecimal(75.65)),
				new Seats(Long.valueOf(34), "D1", "Budget Class",  new BigDecimal(75.65)),
				new Seats(Long.valueOf(35), "D2", "Budget Class",  new BigDecimal(75.65)),
				new Seats(Long.valueOf(36), "D3", "Budget Class",  new BigDecimal(75.65)),
				new Seats(Long.valueOf(37), "D4", "Budget Class",  new BigDecimal(75.65)),
				new Seats(Long.valueOf(38), "D5", "Budget Class",  new BigDecimal(75.65)),
				new Seats(Long.valueOf(39), "D6", "Budget Class",  new BigDecimal(75.65)),
				new Seats(Long.valueOf(40), "D7", "Budget Class",  new BigDecimal(75.65)),
				new Seats(Long.valueOf(41), "D8", "Budget Class",  new BigDecimal(75.65)),
				new Seats(Long.valueOf(42), "D9", "Budget Class",  new BigDecimal(75.65)),
				new Seats(Long.valueOf(43), "D10", "Budget Class",  new BigDecimal(75.65))
				);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() {
		seatsList = new ArrayList<Seats>();
	}

	/**
	 * Test method for {@link com.springboot.justbook.management.controller.SeatsController#getSeats()}.
	 * @throws SeatsMgmtException 
	 * 
	 */
	@Test
	public void testGetSeats() throws SeatsMgmtException {
		
		Mockito.when(seatsRepository.findAll()).thenReturn(seatsList);
		ResponseEntity<MovieMgmtSeatsVOResponseObject> seats = seatsController.getSeats();
		assertNotNull(seats);
		assertThat(seats.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(seats.getBody().getResultList()).isNotEmpty();
	}

}
