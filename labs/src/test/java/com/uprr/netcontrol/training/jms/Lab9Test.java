package com.uprr.netcontrol.training.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.uprr.netcontrol.training.jms.util.JmsConstants;

import junit.framework.Assert;

public class Lab9Test {
	
	private static JmsTemplate jmsTemplate;
	
	@BeforeClass
	public static void setup() throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"application-context.xml"});
		jmsTemplate = context.getBean("jmsTemplate", JmsTemplate.class);
	}
	
	@Test
	public void doRequestReply() throws Exception {
		RequestReplyMessageCreator messageCreator = new RequestReplyMessageCreator();
		TextMessage reply = null;
		//TODO: use the jmsTemplate to do a request/reply
		Assert.assertTrue(reply.getText().contains("<get-time-reply><time>"));
	}
	
	public class RequestReplyMessageCreator implements MessageCreator {
		@Override
		public Message createMessage(Session session) throws JMSException {
			TextMessage msg = session.createTextMessage("<get-time-request/>");
			return msg;
		}
	}

}
