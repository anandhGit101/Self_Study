/**
 * 
 */
package com.springboot.justbook.management.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.justbook.management.domain.Cinemas;
import com.springboot.justbook.management.mapper.MovieMgmtMapper;
import com.springboot.justbook.management.repository.CinemasRepository;
import com.springboot.justbook.management.service.impl.CinemasServiceImpl;
import com.springboot.justbook.vo.MovieMgmtCinemasVOResponseObject;

/**
 * @author M1006601
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CinemasServiceTest {
	
	@InjectMocks
	CinemasServiceImpl cinemasService;
	
	@MockBean
	CinemasRepository cinemasRepository;
	
	@Autowired
	MovieMgmtMapper mapper;
	
	@Test
	public void getAllCinemas() {
		
		List<Cinemas> cinemasList = getCinemas();
		
		MovieMgmtCinemasVOResponseObject response = new MovieMgmtCinemasVOResponseObject();
		response.setResultList(mapper.mapCinemasToCinemaVOList(cinemasList));
		when(cinemasRepository.findAll()).thenReturn(cinemasList);
		assertNotNull(response);
		assertNotNull(response.getResultList());
		assertEquals(3, response.getResultList().size());
	}
	
	@Test
	public void getAllCinemasByCinemasLocation() {
		
		List<Cinemas> cinemasByLocList = getCinemasByLocation();
		MovieMgmtCinemasVOResponseObject response = new MovieMgmtCinemasVOResponseObject();
		response.setResultList(mapper.mapCinemasToCinemaVOList(cinemasByLocList));
		when(cinemasRepository.findAllByCinemasLocation("Chennai")).thenReturn(cinemasByLocList);
		assertNotNull(response);
		assertNotNull(response.getResultList());
		assertEquals(2, response.getResultList().size());
	}
	
	@Test
	public void getCinemasByCinemasName() {
		
		Cinemas cinemasByName = getCinemasByName();
		MovieMgmtCinemasVOResponseObject response = new MovieMgmtCinemasVOResponseObject();
		response.setResultList(mapper.mapCinemasToCinemaVOList(cinemasByName));
		when(cinemasRepository.findCinemasByCinemasName("Luxe")).thenReturn(cinemasByName);
		assertNotNull(response);
		assertNotNull(response.getResultList());
		assertEquals(1, response.getResultList().size());
		assertEquals("Luxe", response.getResultList().get(0).getCinemasName());			
	}

	@Test
	public void  getCinemasById() {
		
		Cinemas cinemasById = getCinemasByCinemasId();
		MovieMgmtCinemasVOResponseObject response = new MovieMgmtCinemasVOResponseObject();
		response.setResultList(mapper.mapCinemasToCinemaVOList(cinemasById));
		when(cinemasRepository.findCinemasByCinemasId(new Long(1))).thenReturn(cinemasById);
		assertNotNull(response);
		assertNotNull(response.getResultList());
		assertEquals(1, response.getResultList().size());
		assertEquals("PVR Cinemas - Grand Mall", response.getResultList().get(0).getCinemasName());
	}
	
	private Cinemas getCinemasByCinemasId() {
		
		Cinemas cinemas = new Cinemas();
		cinemas.setCinemasId(new Long(1));
		cinemas.setCinemasName("PVR Cinemas - Grand Mall");
		cinemas.setCinemasAddress("Grand Mall, Velachery. ");
		cinemas.setCinemasLocation("Chennai");
		return cinemas;
	}

	private Cinemas getCinemasByName() {
		
		Cinemas cinemas = new Cinemas();
		cinemas = new Cinemas();
		cinemas.setCinemasId(new Long(3));
		cinemas.setCinemasName("Luxe");
		cinemas.setCinemasAddress("Jazz Cinemas, Pheonix Market City. ");
		cinemas.setCinemasLocation("Chennai");
		return cinemas;
	}

	private List<Cinemas> getCinemasByLocation() {
		
		List<Cinemas> cinemasList = new ArrayList<>();
		Cinemas cinemas = new Cinemas();
		cinemas.setCinemasId(new Long(1));
		cinemas.setCinemasName("PVR Cinemas - Grand Mall");
		cinemas.setCinemasAddress("Grand Mall, Velachery. ");
		cinemas.setCinemasLocation("Chennai");
		cinemasList.add(cinemas);
			
		cinemas = new Cinemas();
		cinemas.setCinemasId(new Long(2));
		cinemas.setCinemasName("Luxe");
		cinemas.setCinemasAddress("Jazz Cinemas, Pheonix Market City. ");
		cinemas.setCinemasLocation("Chennai");
		cinemasList.add(cinemas);
		return cinemasList;
	}

	private List<Cinemas> getCinemas() {

		List<Cinemas> cinemasList = new ArrayList<>();
		
		Cinemas cinemas = new Cinemas();
		cinemas.setCinemasId(new Long(1));
		cinemas.setCinemasName("PVR Cinemas - Grand Mall");
		cinemas.setCinemasAddress("Grand Mall, Velachery. ");
		cinemas.setCinemasLocation("Chennai");
		cinemasList.add(cinemas);
		
		cinemas = new Cinemas();
		cinemas.setCinemasId(new Long(2));
		cinemas.setCinemasName("PVR Cinemas - Grand Gallada");
		cinemas.setCinemasAddress("Gopalan Mall, Raja Rajeswari Nagar. ");
		cinemas.setCinemasLocation("Bengaluru");
		cinemasList.add(cinemas);
			
		cinemas = new Cinemas();
		cinemas.setCinemasId(new Long(3));
		cinemas.setCinemasName("Luxe");
		cinemas.setCinemasAddress("Jazz Cinemas, Pheonix Market City. ");
		cinemas.setCinemasLocation("Chennai");
		cinemasList.add(cinemas);
		return cinemasList;
	}

}
