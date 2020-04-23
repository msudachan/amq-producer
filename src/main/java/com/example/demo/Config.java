package com.example.demo;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
@EnableJms
public class Config {
	
	@Value("${spring.activemq.broker-url}")
	String BROKER_URL;
	
	@Value("${spring.activemq.user}")
	String BROKER_USERNAME;
	
	@Value("${spring.activemq.password}")
	String BROKER_PASSWORD;

	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(BROKER_URL);
		connectionFactory.setPassword(BROKER_USERNAME);
		connectionFactory.setUserName(BROKER_PASSWORD);
		return connectionFactory;
	}

	@Bean // Serialize message content to json using TextMessage
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setPubSubDomain(true);
		template.setMessageConverter(jacksonJmsMessageConverter());
		return template;
	}

//	@Bean
//	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
//		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//		factory.setConnectionFactory(connectionFactory());
//		factory.setConcurrency("1-1");
//		factory.setPubSubDomain(true);
//		return factory;
//	}

}
