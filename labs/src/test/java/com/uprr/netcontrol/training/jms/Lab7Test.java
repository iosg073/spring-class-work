package com.uprr.netcontrol.training.jms;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.uprr.netcontrol.training.jms.util.JmsConstants;

public class Lab7Test {

	private static JmsUtils jmsUtils;
	
	@BeforeClass
	public static void setup() throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"application-context.xml"});
		jmsUtils = context.getBean("jmsUtils", JmsUtils.class);
	}
	
	@Test
	public void lab7Test() throws Exception {
		if ("<<USER_ID>>.REQUEST".equalsIgnoreCase(JmsConstants.YOUR_QUEUE_NAME)) {
			fail("Please update your queue name");
		}
		jmsUtils.postTextMessage(JmsConstants.YOUR_QUEUE_NAME, "<get-time-request/>");
		//Verify using the Admin client as shown in the slides
	}

}
