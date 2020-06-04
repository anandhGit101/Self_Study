/**
 * 
 */
package com.springboot.justbook.management.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.justbook.management.domain.Seats;
import com.springboot.justbook.management.mapper.SeatsMapper;
import com.springboot.justbook.management.repository.SeatsRepository;
import com.springboot.justbook.management.service.impl.SeatsServiceImpl;
import com.springboot.justbook.vo.MovieMgmtSeatsVOResponseObject;

/**
 * @author M1006601
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SeatsServiceTest {

	@InjectMocks
	SeatsServiceImpl seatsService;
	
	@MockBean
	SeatsRepository seatsRepository;
	
	@Autowired
	SeatsMapper seatsMapper;
	
	@Test
	public void getSeats() {
		
		List<Seats> seatsList = getSeatsList();
		MovieMgmtSeatsVOResponseObject seatsResponse = new MovieMgmtSeatsVOResponseObject();
		seatsResponse.setResultList(seatsMapper.mapSeatsToVOList(seatsList));
		when(seatsRepository.findAll()).thenReturn(seatsList);
		assertNotNull(seatsResponse);
		assertNotNull(seatsResponse.getResultList());
		assertEquals(10, seatsResponse.getResultList().size());
	}

	private List<Seats> getSeatsList() {
		
		List<Seats> seatsList= new ArrayList<>();
		Seats seat = new Seats(Long.valueOf(1), "A1", "First Class", new BigDecimal(183.65));
		seatsList.add(seat);
		seat = new Seats(Long.valueOf(2), "A2", "First Class", new BigDecimal(183.65));
		seatsList.add(seat);
		seat = new Seats(Long.valueOf(3), "A3", "First Class", new BigDecimal(183.65));
		seatsList.add(seat);
		seat = new Seats(Long.valueOf(4), "A4", "First Class", new BigDecimal(183.65));
		seatsList.add(seat);
		seat = new Seats(Long.valueOf(5), "A5", "First Class", new BigDecimal(183.65));
		seatsList.add(seat);
		
		seat = new Seats(Long.valueOf(34), "D1", "Budget Class",  new BigDecimal(75.65));
		seatsList.add(seat);
		seat = new Seats(Long.valueOf(35), "D2", "Budget Class",  new BigDecimal(75.65));
		seatsList.add(seat);
		seat = new Seats(Long.valueOf(36), "D3", "Budget Class",  new BigDecimal(75.65));
		seatsList.add(seat);
		seat = new Seats(Long.valueOf(37), "D4", "Budget Class",  new BigDecimal(75.65));
		seatsList.add(seat);
		seat = new Seats(Long.valueOf(38), "D5", "Budget Class",  new BigDecimal(75.65));
		seatsList.add(seat);
		return seatsList;
	}
}
