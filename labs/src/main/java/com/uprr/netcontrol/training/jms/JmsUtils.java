package com.uprr.netcontrol.training.jms;

import javax.jms.JMSException;
import javax.jms.Message;

public interface JmsUtils {

	void postTextMessage(String queueName, String messageBody) throws JMSException;
	
	Message getMessage(String queueName) throws JMSException; 
	
}
