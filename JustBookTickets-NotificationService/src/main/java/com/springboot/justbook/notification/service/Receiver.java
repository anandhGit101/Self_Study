/**
 * 
 */
package com.springboot.justbook.notification.service;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.springboot.justbook.domain.BookingDetails;

/**
 * @author M1006601
 *
 */
public class Receiver {

	private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

	  private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }
	  
		@Autowired
	    private JavaMailSender javaMailSender;

	  @KafkaListener(topics = "${kafka.topic.json}")
	  public void receive(BookingDetails bookingEntity) {
			logger.info(String.format("$$ -> Consuming Message -> %s",bookingEntity.toString()));
			String subject = "Ticket Confirmed for "+bookingEntity.getShowMovieName()+ "with booking number::"+bookingEntity.getBookingNumber();
			StringBuffer mailText = new StringBuffer();
			logger.info(String.format("$$ -> Preparing mail text ->"));
			mailText.append("Hi "+bookingEntity.getUserName()+",\n\n\t");
			mailText.append("Your ticket booking for "+bookingEntity.getShowMovieName() + "with booking number::"+bookingEntity.getBookingNumber()+"is confirmed.\n");
			mailText.append("Please find the below the ticket details::::\n\n\t");
			mailText.append("Movie Name:::"+bookingEntity.getShowMovieName()+"\n");
			mailText.append("Cinemas Name:::"+bookingEntity.getShowCinemasName()+"\n");
			mailText.append("Booked Date:::"+bookingEntity.getShowDate()+"\n");
			mailText.append("Booked Time:::"+bookingEntity.getShowTimings()+"\n");
			mailText.append("Ticket Category:::"+bookingEntity.getSeatCategory()+"\n");
			mailText.append("Ticket Cost::: Rs."+bookingEntity.getTotalCost()+"\n");
			mailText.append("Seats Selected:::"+bookingEntity.getSeatsSelected()+"\n");
			SimpleMailMessage msg = new SimpleMailMessage();
			logger.info(String.format("$$ -> Preparing mail message ->"));
			msg.setFrom("anand.rajagopalan8527@gmail.com");
			msg.setTo(bookingEntity.getUserEmail());
			msg.setSubject(subject);
			msg.setText(mailText.toString());
			logger.info(String.format("$$ -> Prepared mail message ->"));
			javaMailSender.send(msg);
	  }
}
