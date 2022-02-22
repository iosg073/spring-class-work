package com.uprr.netcontrol.training.jms;

import static org.junit.Assert.*;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.uprr.netcontrol.training.jms.util.JmsConstants;

public class Lab3Test {

	private static JmsUtils jmsUtils;
	
	@BeforeClass
	public static void setup() throws Exception {
		jmsUtils = new RawApiJmsUtils("omhq169a:7222", "training", "parkfair");
	}
	
	@Test
	public void lab3Test() throws Exception {
		//jmsUtils.postTextMessage(JmsConstants.YOUR_QUEUE_NAME, "<get-time-request/>");
		Message message = jmsUtils.getMessage(JmsConstants.YOUR_QUEUE_NAME);
		assertNotNull("Message was null", message);
		TextMessage tMsg = (TextMessage)message;
		assertTrue("No body received", StringUtils.isNotBlank(tMsg.getText()));
		System.out.println(tMsg.getText());
	}
	
	

}
