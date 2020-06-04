/**
 * 
 */
package com.springboot.sample.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author M1006601
 *
 */
@Service
public class KafkaMessageProducer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaMessageProducer.class);
	private static final String TOPIC = "booking";

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String message) {
		logger.info(String.format("$$ -> Producing message --> %s", message));
		this.kafkaTemplate.send(TOPIC, message);
	}

}
