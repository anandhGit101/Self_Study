package com.springboot.justbook.controller;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureWebMvc
@Ignore
public class TicketBookingControllerTests {
	
	@InjectMocks
	TicketBookingController ticketBookingController;
	

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetBookingDetailsByBookingNumber() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetBookingDetailsByUserName() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetBookingDetailsByUserPhoneNumber() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testBookTickets() {
		fail("Not yet implemented"); // TODO
	}

}
