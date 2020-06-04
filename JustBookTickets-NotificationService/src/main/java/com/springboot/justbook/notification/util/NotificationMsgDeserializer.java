/**
 * 
 */
package com.springboot.justbook.notification.util;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author M1006601
 *
 */
public class NotificationMsgDeserializer<BookingDetails> implements Deserializer<BookingDetails> {

	private final Logger logger = LoggerFactory.getLogger(NotificationMsgDeserializer.class);
	
	private Class <BookingDetails> type;

    public NotificationMsgDeserializer() {
    }

    public NotificationMsgDeserializer(Class type) {
    	this.type=type;
    }

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {

	}

	@Override
	public BookingDetails deserialize(String topic, byte[] data) {
		
		ObjectMapper mapper = new ObjectMapper();
		BookingDetails obj = null;
        try {
            obj = mapper.readValue(data, type);
        } catch (Exception e) {

            logger.error(e.getMessage());
        }
        return obj;
	}

	@Override
	public void close() {
		
	}

}
