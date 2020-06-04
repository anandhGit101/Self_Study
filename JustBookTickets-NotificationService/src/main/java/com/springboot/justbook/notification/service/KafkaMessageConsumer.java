/**
 * 
 */
package com.springboot.justbook.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.springboot.justbook.domain.BookingDetails;

/**
 * @author M1006601
 *
 */
//@Service
public class KafkaMessageConsumer {
	
	private final Logger logger = LoggerFactory.getLogger(KafkaMessageConsumer.class);
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	/*@KafkaListener(topics = "booking", groupId = "group_id")
	public void consume(String message){
		logger.info("$$Inside the "+getClass().getName()+"$$");
		logger.info(String.format("$$ -> Consumed Message -> %s",message));
	}*/
	
	@KafkaListener(topics = "booking", groupId = "json")
	public void consumeBookingDetails(BookingDetails bookingEntity) {
		logger.info(String.format("$$ -> Consuming Message -> %s",bookingEntity.toString()));
		String subject = "Ticket Confirmed for "+bookingEntity.getShowMovieName()+ "with booking number::"+bookingEntity.getBookingNumber();
		StringBuffer mailText = new StringBuffer();
		logger.info(String.format("$$ -> Preparing mail text -> %s"));
		mailText.append("Hi "+bookingEntity.getUserName()+",\n\n\t");
		mailText.append("Your ticket booking for "+bookingEntity.getShowMovieName() + "with booking number::"+bookingEntity.getBookingNumber()+"is confirmed.\n");
		mailText.append("Please find the below the ticket details::::\n\n\t");
		mailText.append("Movie Name:::"+bookingEntity.getShowMovieName()+"\n");
		mailText.append("Cinemas Name:::"+bookingEntity.getShowCinemasName()+"\n");
		mailText.append("Booked Date:::"+bookingEntity.getShowDate()+"\n");
		mailText.append("Booked Time:::"+bookingEntity.getShowTimings()+"\n");
		mailText.append("Ticket Category:::"+bookingEntity.getSeatCategory()+"\n");
		mailText.append("Ticket Cost::: Rs."+bookingEntity.getTotalCost()+"\n");
		mailText.append("Seats Selected::: Rs."+bookingEntity.getSeatsSelected()+"\n");
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("anandh.ragavan@gmail.com");
		msg.setTo(bookingEntity.getUserEmail());
		msg.setSubject(subject);
		msg.setText(mailText.toString());
		
		javaMailSender.send(msg);
	}
}