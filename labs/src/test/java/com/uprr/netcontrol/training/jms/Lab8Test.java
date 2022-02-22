package com.uprr.netcontrol.training.jms;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.uprr.netcontrol.training.jms.util.JmsConstants;

public class Lab8Test {

	private static JmsUtils jmsUtils;
	
	@BeforeClass
	public static void setup() throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"application-context.xml"});
		jmsUtils = context.getBean("jmsUtils", JmsUtils.class);
	}

	@Test
	public void lab8Test() throws Exception {
		jmsUtils.postTextMessage(JmsConstants.YOUR_QUEUE_NAME, "<get-time-request/>");
		Message message = jmsUtils.getMessage(JmsConstants.YOUR_QUEUE_NAME);
		assertNotNull("Message was null", message);
		TextMessage tMsg = (TextMessage)message;
		assertTrue("No body received", StringUtils.isNotBlank(tMsg.getText()));
		System.out.println(tMsg.getText());
	}
	
	

}
