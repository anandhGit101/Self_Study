/**
 * 
 */
package com.springboot.justbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.springboot.justbook.service.TicketBookingService;
import com.springboot.justbook.service.impl.TicketBookingServiceImpl;

/**
 * @author M1006601
 *
 */

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
@ComponentScan({"com.springboot.justbook"})
public class PersistenceConfig {
	
	public PersistenceConfig() {
        super();
    }
	
	
	@Bean
	public TicketBookingService ticketBookingService() {
		
		return new TicketBookingServiceImpl();
	}
	
}