package com.uprr.netcontrol.training.jms;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

import com.uprr.netcontrol.training.jms.util.JmsConstants;

public class Lab2Test {

	private static JmsUtils jmsUtils;
	
	@BeforeClass
	public static void setup() throws Exception {
		jmsUtils = new RawApiJmsUtils("omhq169a:7222", "training", "parkfair");
	}
	
	@Test
	public void lab2Test() throws Exception {
		if ("<<USER_ID>>.REQUEST".equalsIgnoreCase(JmsConstants.YOUR_QUEUE_NAME)) {
			fail("Please update your queue name");
		}
		jmsUtils.postTextMessage(JmsConstants.YOUR_QUEUE_NAME, "Igem 152+++++++++++");
		//Verify using the Admin client as shown in the slides
	}
	
}
