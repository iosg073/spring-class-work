package com.uprr.netcontrol.training.jms;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.jms.core.JmsTemplate;

public class SpringJmsUtils implements JmsUtils {
	
	private JmsTemplate jmsTemplate;

	public SpringJmsUtils(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	
	@Override
	public void postTextMessage(String queueName, final String messageBody)
			throws JMSException {

	}

	@Override
	public Message getMessage(String queueName) throws JMSException {
		return null;
	}
	
}
