/**
 * 
 */
package com.sapient.service.jms;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

/**
 * @author gsing5
 * Basic JMS Message producer service implementation 
 */

@Service("basicJmsService")
public class BasicJMSClientService implements JMSClientService {

	private static final Logger logger = LoggerFactory
			.getLogger(JMSClientService.class);

	@Autowired
	private JmsTemplate template = null;

	@Override
	public void sendToQueue(final String text) {
		if (text == null)
			throw new IllegalArgumentException("Input is null");

		logger.info("Sending Message : " + text);
		template.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage(text);
				// set default properties for message
				return message;
			}
		});
		logger.info("Sending Successful. ");

	}

	@Override
	public void sendToQueue(final Map<String, String> map) {
		if (map == null)
			throw new IllegalArgumentException("Input is null");
		
		logger.info("Sending Map Message : " + map.toString());

		template.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				MapMessage message = session.createMapMessage();
				// set default properties for message
				for(String key : map.keySet())
					message.setString(key, map.get(key));
				return message;
			}
		});
		
		logger.info("Sending Successful. ");
	}
	
	public void setTemplate(JmsTemplate template) {
		this.template = template;
	}

}
