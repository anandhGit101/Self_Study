/**
 * 
 */
package com.springboot.justbook.notification.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.springboot.justbook.domain.BookingDetails;

/**
 * @author M1006601
 *
 */
@Configuration
@EnableKafka
public class KafkaMessageConsumerConfig {

	  @Value("${kafka.bootstrap-servers}")
	  private String bootstrapServers;

	  @Bean
	  public ConsumerFactory<String, BookingDetails> consumerFactory() {
		  JsonDeserializer<BookingDetails> deserializer = new JsonDeserializer<>(BookingDetails.class);
	        deserializer.setRemoveTypeHeaders(false);
	        deserializer.addTrustedPackages("*");
	        deserializer.setUseTypeMapperForKey(true);
	        Map<String, Object> props = new HashMap<>();
	        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
	        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
	        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
	        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
	        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
	  }

	  @Bean
	  public ConcurrentKafkaListenerContainerFactory<String, BookingDetails> kafkaListenerContainerFactory() {
	    ConcurrentKafkaListenerContainerFactory<String, BookingDetails> factory =
	        new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(consumerFactory());

	    return factory;
	  }

	  @Bean
	  public Receiver receiver() {
	    return new Receiver();
	  }
}
