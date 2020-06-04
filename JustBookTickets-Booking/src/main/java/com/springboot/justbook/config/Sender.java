package com.springboot.justbook.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import com.springboot.justbook.domain.BookingDetails;

public class Sender {

	private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

	@Value("${kafka.topic.json}")
	private String jsonTopic;

	@Autowired
	private KafkaTemplate<String, BookingDetails> kafkaTemplate;

	public void send(BookingDetails details) {
		LOGGER.info("sending booking ticket details='{}'", details.toString());
		kafkaTemplate.send(jsonTopic, details);
	}

}
