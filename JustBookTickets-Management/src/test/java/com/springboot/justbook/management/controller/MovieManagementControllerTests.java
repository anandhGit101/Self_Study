/**
 * 
 */
package com.springboot.justbook.management.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

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

import com.springboot.justbook.management.domain.Cinemas;
import com.springboot.justbook.management.domain.Movie;
import com.springboot.justbook.management.domain.MovieSchedule;
import com.springboot.justbook.management.exception.MovieMgmtException;
import com.springboot.justbook.management.mapper.MovieMgmtMapper;
import com.springboot.justbook.management.repository.CinemasRepository;
import com.springboot.justbook.management.repository.MovieRepository;
import com.springboot.justbook.management.repository.MovieScheduleRepository;
import com.springboot.justbook.management.service.CinemasService;
import com.springboot.justbook.management.service.MovieScheduleService;
import com.springboot.justbook.management.service.MovieService;
import com.springboot.justbook.vo.CinemasVO;
import com.springboot.justbook.vo.MovieMgmtCinemasVOResponseObject;
import com.springboot.justbook.vo.MovieMgmtMovieScheduleVOResponseObject;
import com.springboot.justbook.vo.MovieMgmtMovieVOResponseObject;
import com.springboot.justbook.vo.MovieVO;

/**
 * @author M1006601
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureWebMvc
//@Ignore
public class MovieManagementControllerTests {

	
	
	/*@Autowired 
	private TestRestTemplate restTemplate;
	
	@Autowired
    private MockMvc mockMvc;*/
	
	@InjectMocks
	MovieManagementController movieMgmtController;
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	CinemasService cinemasService;
	
	@Autowired
	MovieScheduleService scheduleService;
	
	
	@Mock
	MovieRepository movieRepository;
	
	@Mock
	CinemasRepository cinemasRepository;
	
	@Mock
	MovieScheduleRepository scheduleRepository;
	
	@Mock
	HttpServletResponse response;
	
	@Mock
	MovieMgmtMapper mapper;

	ResponseEntity<MovieMgmtMovieScheduleVOResponseObject> responseObject;
	
	MovieMgmtMovieScheduleVOResponseObject responseObj = new MovieMgmtMovieScheduleVOResponseObject();
	
	/*MovieVO movVO = new MovieVO(Long.valueOf(1), "Billa 2", "Ajithkumar, Parvathy Omanakuttan, Bruna, Ilavarasu",
			"Billa 2 is a 2012 Indian Tamil-language action thriller film directed by Chakri Toleti.", "Tamil",
			LocalDate.now(), "Action-Thriller");
	
	MovieVO movVO2 = new MovieVO(Long.valueOf(2), "3 Idiots",
			"Aamir Khan, R. Madhavan, Sharman Joshi, Kareena Kapoor, Boman Irani and Omi Vaidya",
			"3 Idiots is a 2009 Indian Hindi-language coming-of-age comedy-drama film co-written (with Abhijat Joshi) and directed by Rajkumar Hirani.",
			"Hindi", LocalDate.now(), "Comedy-Drama");*/
	
	@Before
	public void setup() {
		ReflectionTestUtils.setField(movieMgmtController, "movieService", movieService);
		ReflectionTestUtils.setField(movieService, "movieRepository", movieRepository);
		ReflectionTestUtils.setField(movieMgmtController, "cinemasService", cinemasService);
		ReflectionTestUtils.setField(cinemasService, "cinemasRepository", cinemasRepository);
		ReflectionTestUtils.setField(movieMgmtController, "scheduleService", scheduleService);
		ReflectionTestUtils.setField(scheduleService, "scheduleRepository", scheduleRepository);
		ReflectionTestUtils.setField(scheduleService, "cinemasRepository", cinemasRepository);
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	
	
	/**
	 * Test method for {@link com.springboot.justbook.management.controller.MovieManagementController#chooseMovie()}.
	 * @throws Exception 
	 */
	@Test
	public void testChooseMovie() throws Exception {
		
		List<Movie> resultList = Arrays.asList(
				new Movie(Long.valueOf(1), "Billa 2", "Ajithkumar, Parvathy Omanakuttan, Bruna, Ilavarasu",
						"Billa 2 is a 2012 Indian Tamil-language action thriller film directed by Chakri Toleti.", "Tamil",
						LocalDate.parse("2012-07-13", DateTimeFormatter.ISO_DATE), "Action-Thriller",null,true),
				new Movie(Long.valueOf(2), "3 Idiots",
						"Aamir Khan, R. Madhavan, Sharman Joshi, Kareena Kapoor, Boman Irani and Omi Vaidya",
						"3 Idiots is a 2009 Indian Hindi-language coming-of-age comedy-drama film co-written (with Abhijat Joshi) and directed by Rajkumar Hirani.",
						"Hindi", LocalDate.parse("2003-01-15", DateTimeFormatter.ISO_DATE), "Comedy-Drama",null,true));
	

		Mockito.when(movieRepository.findByIsMovieActiveTrue()).thenReturn(resultList);
 		ResponseEntity<MovieMgmtMovieVOResponseObject> chooseMovie = movieMgmtController.chooseMovie();
		assertNotNull(chooseMovie);
		assertThat(chooseMovie.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(chooseMovie.getBody().getResultList()).isNotEmpty();
		//this.mockMvc.perform(get("/jbtMovieMgmt/listMovies")).andExpect(status().isOk());
	}

	/**
	 * Test method for {@link com.springboot.justbook.management.controller.MovieManagementController#chooseCinemas()}.
	 * @throws Exception 
	 */
	@Test
	public void testChooseCinemas() throws Exception {
		
		List<Cinemas> cinemasList = Arrays.asList(
				new Cinemas(Long.valueOf(1), "Marlen Cinemas", "Malleswaram", "Bengaluru"),
				new Cinemas(Long.valueOf(2), "Rohini Silver Screens", "Poonamallee High Road, Koyambedu", "Chennai"));
		
		Mockito.when(cinemasRepository.findAll()).thenReturn(cinemasList);
		ResponseEntity<MovieMgmtCinemasVOResponseObject> chooseCinemas = movieMgmtController.chooseCinemas();
		assertNotNull(chooseCinemas);
		assertThat(chooseCinemas.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(chooseCinemas.getBody().getResultList()).isNotEmpty();
	}

	/**
	 * Test method for {@link com.springboot.justbook.management.controller.MovieManagementController#chooseCinemasByCinemasLocation(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public void testChooseCinemasByCinemasLocation() throws Exception {
		
		List<Cinemas> cinemasList = Arrays.asList(
				new Cinemas(Long.valueOf(1), "Marlen Cinemas", "Malleswaram", "Bengaluru"));
		Mockito.when(cinemasRepository.findAllByCinemasLocation(ArgumentMatchers.anyString())).thenReturn(cinemasList);
		ResponseEntity<MovieMgmtCinemasVOResponseObject> cinemasByCinemasLocation = movieMgmtController.chooseCinemasByCinemasLocation("Bengaluru");
		assertNotNull(cinemasByCinemasLocation);
		assertThat(cinemasByCinemasLocation.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(cinemasByCinemasLocation.getBody().getResultList()).isNotEmpty();
		CinemasVO cinemasVO = (CinemasVO) cinemasByCinemasLocation.getBody().getResultList().get(0);
		assertNotNull(cinemasVO);
		assertThat(cinemasVO.getCinemasName()).isEqualTo("Marlen Cinemas");
	}

	/**
	 * Test method for {@link com.springboot.justbook.management.controller.MovieManagementController#chooseCinemasByCinemasName(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public void testChooseCinemasByCinemasName() throws Exception {
		
		List<Cinemas> cinemasList = Arrays.asList(
				new Cinemas(Long.valueOf(1), "Vetri", "Vetri Theatre Complex, Chromepet", "Chennai"));
		Mockito.when(cinemasRepository.findByCinemasName(ArgumentMatchers.anyString())).thenReturn(cinemasList);
		ResponseEntity<MovieMgmtCinemasVOResponseObject> cinemasByCinemasLocation = movieMgmtController.chooseCinemasByCinemasName("Vetri");
		assertNotNull(cinemasByCinemasLocation);
		assertThat(cinemasByCinemasLocation.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(cinemasByCinemasLocation.getBody().getResultList()).isNotEmpty();
		CinemasVO cinemasVO = (CinemasVO) cinemasByCinemasLocation.getBody().getResultList().get(0);
		assertNotNull(cinemasVO);
		assertThat(cinemasVO.getCinemasLocation()).isEqualTo("Chennai");
		assertThat(cinemasVO.getCinemasAddress()).isEqualTo("Vetri Theatre Complex, Chromepet");
	}

	/**
	 * Test method for {@link com.springboot.justbook.management.controller.MovieManagementController#getScheduleByCinemas(java.lang.Long)}.
	 */
	
	  @Test
	  public void testGetScheduleByCinemas() throws Exception{
	  
		List<MovieSchedule> scheduleList = Arrays.asList(new MovieSchedule(Long.valueOf(1),
				new Movie(Long.valueOf(1), "Aasal", "Ajithkumar, Sameera Reddy, Bhavana, Prabhu",
						"Aasal is a 2010 Indian Tamil-language action thriller film directed by Saran.",
						"Tamil", LocalDate.parse("2010-02-05", DateTimeFormatter.ISO_DATE), "Action-Thriller",null,true),
				new Cinemas(Long.valueOf(1), "Vetri", "Vetri Theatre Complex, Chromepet", "Chennai"),
				LocalTime.parse("11:15"), LocalDate.parse("2019-09-14", DateTimeFormatter.ISO_DATE), 60));
		Mockito.when(scheduleRepository.getMovieScheduleByCinemasId(ArgumentMatchers.anyLong()))
				.thenReturn(scheduleList);
		ResponseEntity<MovieMgmtMovieScheduleVOResponseObject> scheduleByCinemasId = movieMgmtController
				.getScheduleByCinemasId(Long.valueOf(1));
		assertNotNull(scheduleByCinemasId);
		
	  }
	 

	/**
	 * Test method for {@link com.springboot.justbook.management.controller.MovieManagementController#getScheduleByCinemasName(java.lang.String)}.
	 * @throws MovieMgmtException
	 * 
	 */
	
	@Test
	public void testGetScheduleByCinemasName() throws MovieMgmtException {

		List<MovieSchedule> scheduleList = Arrays.asList(new MovieSchedule(Long.valueOf(2),
				new Movie(Long.valueOf(2), "Anbe Sivam", "Kamal Haasan,R. Madhavan, Kiran Rathod",
				"Anbe Sivam is a 2003 Indian Tamil-language comedy-drama film directed and co-produced by Sundar C. with K. Muralitharan, "
						+ "V. Swaminathan and G. Venugopal under the banner of Lakshmi Movie Makers.",
				"Tamil", LocalDate.parse("2003-01-15", DateTimeFormatter.ISO_DATE), "Comedy-Drama",null,true),
				new Cinemas(Long.valueOf(1), "Vetri Cinemas", "Vetri Theatre Complex, Chromepet", "Chennai"),
				LocalTime.parse("11:15"), LocalDate.parse("2019-09-14", DateTimeFormatter.ISO_DATE), 60));
		Cinemas cine = new Cinemas(Long.valueOf(1), "Vetri Cinemas", "Vetri Theatre Complex, Chromepet", "Chennai");
		cine.setMovieSchedule(scheduleList);
		Mockito.when(cinemasRepository.findCinemasByCinemasName(ArgumentMatchers.anyString())).thenReturn(cine);
		ResponseEntity<MovieMgmtMovieScheduleVOResponseObject> scheduleByCinemasName = movieMgmtController.getScheduleByCinemasName("Vetri Cinemas");
		assertNotNull(scheduleByCinemasName);
		
	}

	/**
	 * Test method for {@link com.springboot.justbook.management.controller.MovieManagementController#getScheduleByMovieName(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public void testGetScheduleByMovieName() throws Exception {

		List<MovieSchedule> scheduleList = Arrays.asList(new MovieSchedule(Long.valueOf(1),
				new Movie(Long.valueOf(1), "Viswasam", "Ajithkumar, Nayanthara, Jagapathi Babu",
						"Viswasam is a 2019 Indian Tamil-language action drama film written and directed by Siva. ",
						"Tamil", LocalDate.parse("2019-01-10", DateTimeFormatter.ISO_DATE), "Action-Drama",null,true),
				new Cinemas(Long.valueOf(1), "Vetri", "Vetri Theatre Complex, Chromepet", "Chennai"),
				LocalTime.parse("11:15"), LocalDate.parse("2019-09-14", DateTimeFormatter.ISO_DATE), 60));
		Mockito.when(scheduleRepository.getMovieScheduleByMovieId(ArgumentMatchers.anyLong()))
				.thenReturn(scheduleList);
		ResponseEntity<MovieMgmtMovieScheduleVOResponseObject> scheduleByMovieName = movieMgmtController.getScheduleByMovieName("Viswasam");
		assertNotNull(scheduleByMovieName);
	}

	/**
	 * Test method for {@link com.springboot.justbook.management.controller.MovieManagementController#getScheduleByCinemasNameAndMovieName(java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public void testGetScheduleByCinemasNameAndMovieName() throws Exception {

		List<MovieSchedule> scheduleList = Arrays.asList(new MovieSchedule(Long.valueOf(2), new Movie(Long.valueOf(2),
				"Anbe Sivam", "Kamal Haasan,R. Madhavan, Kiran Rathod",
				"Anbe Sivam is a 2003 Indian Tamil-language comedy-drama film directed and co-produced by Sundar C. with K. Muralitharan, "
						+ "V. Swaminathan and G. Venugopal under the banner of Lakshmi Movie Makers.",
				"Tamil", LocalDate.parse("2003-01-15", DateTimeFormatter.ISO_DATE), "Comedy-Drama",null,true),
				new Cinemas(Long.valueOf(1), "Vetri", "Vetri Theatre Complex, Chromepet", "Chennai"),
				LocalTime.parse("11:15"), LocalDate.parse("2019-09-14", DateTimeFormatter.ISO_DATE), 60));
		Mockito.when(scheduleRepository.getMovieScheduleByMovieIdAndCinemasId(ArgumentMatchers.anyLong(),
				ArgumentMatchers.anyLong())).thenReturn(scheduleList);
		ResponseEntity<MovieMgmtMovieScheduleVOResponseObject> scheduleByCinemasNameAndMovieName = movieMgmtController.getScheduleByCinemasNameAndMovieName("Anbe Sivam", "Vetri");
		assertNotNull(scheduleByCinemasNameAndMovieName);
	}

	/**
	 * Test method for {@link com.springboot.justbook.management.controller.MovieManagementController#getScheduleByCinemasIdAndMovieId(java.lang.Long, java.lang.Long)}.
	 * @throws Exception 
	 */
	@Test
	public void testGetScheduleByCinemasIdAndMovieId() throws Exception {
		
		List<MovieSchedule> scheduleList = Arrays.asList(new MovieSchedule(Long.valueOf(1),
				new Movie(Long.valueOf(1), "Billa 2", "Ajithkumar, Parvathy Omanakuttan, Bruna, Ilavarasu",
						"Billa 2 is a 2012 Indian Tamil-language action thriller film directed by Chakri Toleti.",
						"Tamil", LocalDate.parse("2012-07-13", DateTimeFormatter.ISO_DATE), "Action-Thriller",null,true),
				new Cinemas(Long.valueOf(1), "Vetri", "Vetri Theatre Complex, Chromepet", "Chennai"),
				LocalTime.parse("11:15"), LocalDate.parse("2019-09-14", DateTimeFormatter.ISO_DATE), 60));
		Mockito.when(scheduleRepository.getMovieScheduleByMovieIdAndCinemasId(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()))
				.thenReturn(scheduleList);
		ResponseEntity<MovieMgmtMovieScheduleVOResponseObject> scheduleByCinemasIdAndMovieId = movieMgmtController.getScheduleByCinemasIdAndMovieId(new Long(1), new Long(1));
		assertNotNull(scheduleByCinemasIdAndMovieId);
	}

	/**
	 * Test method for {@link com.springboot.justbook.management.controller.MovieManagementController#addMovieEntry(com.springboot.justbook.management.vo.MovieVO, javax.servlet.http.HttpServletResponse)}.
	 * @throws Exception 
	 */
	@Test
	public void testAddMovieEntry() throws Exception {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        
        MovieVO movieVO = new MovieVO(Long.valueOf(1), "Aasal", "Ajithkumar, Sameera Reddy, Bhavana, Prabhu",
				"Aasal is a 2010 Indian Tamil-language action thriller film directed by Saran.",
				"Tamil", LocalDate.parse("2010-02-05", DateTimeFormatter.ISO_DATE), "Action-Thriller", null,true);
        
        Movie movie = new Movie(Long.valueOf(1), "Aasal", "Ajithkumar, Sameera Reddy, Bhavana, Prabhu",
				"Aasal is a 2010 Indian Tamil-language action thriller film directed by Saran.",
				"Tamil", LocalDate.parse("2010-02-05", DateTimeFormatter.ISO_DATE), "Action-Thriller",null,true);
        
        Mockito.when(movieRepository.findMovieByMovieTitleAndIsMovieActiveTrue(ArgumentMatchers.anyString())).thenReturn(null);
        Mockito.when(mapper.mapMovieVOToMovie(movieVO)).thenReturn(movie);
        ResponseEntity<MovieMgmtMovieVOResponseObject> addMovieEntry = movieMgmtController.addMovieEntry(movieVO, response);
        Mockito.when(movieRepository.save(movie)).thenReturn(movie);
        assertNotNull(addMovieEntry);
        assertThat(addMovieEntry.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}

	/**
	 * Test method for {@link com.springboot.justbook.management.controller.MovieManagementController#addCinemasEntry(com.springboot.justbook.management.vo.CinemasVO, javax.servlet.http.HttpServletResponse)}.
	 */
	@Test
	public void testAddCinemasEntry() {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        
        CinemasVO cineVO = new CinemasVO();
        cineVO.setCinemasId(Long.valueOf(1));
        cineVO.setCinemasName("Abirami Cineplex");
        cineVO.setCinemasAddress("Abi & Abi Complex, Mount Road");
        cineVO.setCinemasLocation("Chennai");
        
        Cinemas cine = new Cinemas(Long.valueOf(1), "Abirami Cineplex", "Abi & Abi Complex, Mount Road", "Chennai");
        
        Mockito.when(cinemasRepository.findCinemasByCinemasName(ArgumentMatchers.anyString())).thenReturn(null);
        Mockito.when(mapper.mapCinemasVOToCinema(cineVO)).thenReturn(cine);
        ResponseEntity<MovieMgmtCinemasVOResponseObject> addCinemasEntry = movieMgmtController.addCinemasEntry(cineVO, response);
        Mockito.when(cinemasRepository.save(cine)).thenReturn(cine);
        assertNotNull(addCinemasEntry);
        assertThat(addCinemasEntry.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

	/**
	 * Test method for {@link com.springboot.justbook.management.controller.MovieManagementController#deleteCinemasEntity(java.lang.Long)}.
	 */
	//@Test
	public void testDeleteCinemasEntity() {
		
		
	}

	/**
	 * Test method for {@link com.springboot.justbook.management.controller.MovieManagementController#deleteMovieEntity(java.lang.Long)}.
	 */
	//@Test
	public void testDeleteMovieEntity() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.springboot.justbook.management.controller.MovieManagementController#getScheduleByMovieId(java.lang.Long)}.
	 * @throws Exception 
	 */
	@Test
	public void testGetScheduleByMovieId() throws Exception {
		
		List<MovieSchedule> scheduleList = Arrays.asList(new MovieSchedule(Long.valueOf(1),
				new Movie(Long.valueOf(1), "Billa 2", "Ajithkumar, Parvathy Omanakuttan, Bruna, Ilavarasu",
						"Billa 2 is a 2012 Indian Tamil-language action thriller film directed by Chakri Toleti.",
						"Tamil", LocalDate.parse("2012-07-13", DateTimeFormatter.ISO_DATE), "Action-Thriller",null,true),
				new Cinemas(Long.valueOf(1), "Vetri", "Vetri Theatre Complex, Chromepet", "Chennai"),
				LocalTime.parse("11:15"), LocalDate.parse("2019-09-14", DateTimeFormatter.ISO_DATE), 60),
				new MovieSchedule(Long.valueOf(2),
						new Movie(Long.valueOf(1), "Billa 2", "Ajithkumar, Parvathy Omanakuttan, Bruna, Ilavarasu",
								"Billa 2 is a 2012 Indian Tamil-language action thriller film directed by Chakri Toleti.",
								"Tamil", LocalDate.parse("2012-07-13", DateTimeFormatter.ISO_DATE), "Action-Thriller",null,true),
						new Cinemas(Long.valueOf(1), "Vetri", "Vetri Theatre Complex, Chromepet", "Chennai"),
						LocalTime.parse("14:15"), LocalDate.parse("2019-09-14", DateTimeFormatter.ISO_DATE), 60));
		Mockito.when(scheduleRepository.getMovieScheduleByMovieId(ArgumentMatchers.anyLong()))
				.thenReturn(scheduleList);
		ResponseEntity<MovieMgmtMovieScheduleVOResponseObject> scheduleByMovieId = movieMgmtController.getScheduleByMovieId(Long.valueOf(1));
		assertNotNull(scheduleByMovieId);
	}

	/**
	 * Test method for {@link com.springboot.justbook.management.controller.MovieManagementController#getScheduleByCinemasId(java.lang.Long)}.
	 * @throws Exception 
	 */
	@Test
	public void testGetScheduleByCinemasId() throws Exception {
		
		List<MovieSchedule> scheduleList = Arrays.asList(new MovieSchedule(Long.valueOf(1),
				new Movie(Long.valueOf(1), "Billa 2", "Ajithkumar, Parvathy Omanakuttan, Bruna, Ilavarasu",
						"Billa 2 is a 2012 Indian Tamil-language action thriller film directed by Chakri Toleti.",
						"Tamil", LocalDate.parse("2012-07-13", DateTimeFormatter.ISO_DATE), "Action-Thriller",null,true),
				new Cinemas(Long.valueOf(1), "Vetri", "Vetri Theatre Complex, Chromepet", "Chennai"),
				LocalTime.parse("11:15"), LocalDate.parse("2019-09-14", DateTimeFormatter.ISO_DATE), 60),
				new MovieSchedule(Long.valueOf(2),
						new Movie(Long.valueOf(1), "Billa 2", "Ajithkumar, Parvathy Omanakuttan, Bruna, Ilavarasu",
								"Billa 2 is a 2012 Indian Tamil-language action thriller film directed by Chakri Toleti.",
								"Tamil", LocalDate.parse("2012-07-13", DateTimeFormatter.ISO_DATE), "Action-Thriller",null,true),
						new Cinemas(Long.valueOf(1), "Vetri", "Vetri Theatre Complex, Chromepet", "Chennai"),
						LocalTime.parse("14:15"), LocalDate.parse("2019-09-14", DateTimeFormatter.ISO_DATE), 60));
		Mockito.when(scheduleRepository.getMovieScheduleByCinemasId(Long.valueOf(1))).thenReturn(scheduleList);
		ResponseEntity<MovieMgmtMovieScheduleVOResponseObject> scheduleByCinemas = movieMgmtController.getScheduleByCinemasId(Long.valueOf(1));
		assertNotNull(scheduleByCinemas);
	}

}
