package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Publisher {
	@Autowired
	JmsTemplate jmsTemplate;

	@Value("${spring.activemq.topic.name}")
	String topic;

	public void send(Company company) {
		jmsTemplate.convertAndSend(topic, company);
		System.out.println("Company::" + company + "Published successfully");
	}
}
