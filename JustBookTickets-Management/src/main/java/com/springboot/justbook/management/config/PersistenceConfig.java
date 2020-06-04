/**
 * 
 */
package com.springboot.justbook.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.springboot.justbook.management.service.CinemasService;
import com.springboot.justbook.management.service.MovieScheduleService;
import com.springboot.justbook.management.service.MovieService;
import com.springboot.justbook.management.service.SeatScheduleService;
import com.springboot.justbook.management.service.SeatsService;
import com.springboot.justbook.management.service.impl.CinemasServiceImpl;
import com.springboot.justbook.management.service.impl.MovieScheduleServiceImpl;
import com.springboot.justbook.management.service.impl.MovieServiceImpl;
import com.springboot.justbook.management.service.impl.SeatScheduleServiceImpl;
import com.springboot.justbook.management.service.impl.SeatsServiceImpl;

/**
 * @author M1006601
 *
 */

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
@ComponentScan({"com.springboot.justbook.management"})
public class PersistenceConfig {
	
	public PersistenceConfig() {
        super();
    }
	
	
	@Bean
	public MovieService movieService() {
		
		return new MovieServiceImpl();
	}
	
	public CinemasService cinemasService() {
		
		return new CinemasServiceImpl();
	}
	
	
	public MovieScheduleService movieScheduleService() {
		
		return new MovieScheduleServiceImpl();
	}

	public SeatsService seatsService() {
		
		return new SeatsServiceImpl();
	}
	
	public SeatScheduleService seatScheduleService() {
		
		return new SeatScheduleServiceImpl();
	}
}
