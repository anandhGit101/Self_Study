/**
 * 
 */
package com.springboot.justbook.notification.service;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author M1006601
 *
 */
public class NotificationMsgSerializer  implements Serializer {

	private final Logger logger = LoggerFactory.getLogger(NotificationMsgSerializer.class);
	
	@Override
	public void configure(Map configs, boolean isKey) {
		
	}

	@Override
	public byte[] serialize(String topic, Object data) {
		
		byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return retVal;
	}

	@Override
	public void close() {
		
	}

}
