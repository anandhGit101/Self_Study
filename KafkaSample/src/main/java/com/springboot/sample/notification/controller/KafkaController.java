/**
 * 
 */
package com.springboot.sample.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.sample.notification.KafkaMessageProducer;

/**
 * @author M1006601
 *
 */
@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
	private final KafkaMessageProducer producer;

	@Autowired
	public KafkaController(KafkaMessageProducer producer) {
		this.producer = producer;
	}

	@PostMapping(value = "/publish")
	public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
		this.producer.sendMessage(message);
	}
}