/**
 * 
 */
package com.springboot.sample.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author M1006601
 *
 */
//@Service
public class KafkaMessageConsumer {
	
	private final Logger logger = LoggerFactory.getLogger(KafkaMessageConsumer.class);
	
	//@KafkaListener(topics = "booking", groupId = "group_id")
	public void consume(String message){
	logger.info(String.format("$$ -> Consumed Message -> %s",message));
	}

}
