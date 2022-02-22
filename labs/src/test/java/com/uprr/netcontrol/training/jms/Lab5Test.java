package com.uprr.netcontrol.training.jms;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueRequestor;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.Test;

import com.uprr.netcontrol.training.jms.util.JmsConstants;

import junit.framework.Assert;

public class Lab5Test {

	private static final String JMS_URL = "omhq169a:7222";
	private static final String JMS_USER = "training";
	private static final String JMS_PWD = "parkfair";
	private QueueConnection connection;
	private QueueConnectionFactory factory=new com.tibco.tibjms.TibjmsQueueConnectionFactory(JMS_URL);;
	@Test
	public void lab5Test() throws Exception {
		new Lab4Test().lab4Test();
//		QueueRequestor requestor = new QueueRequestor(null, null);
//		TextMessage reply = (TextMessage)requestor.request(null);
		//Assert.assertTrue(reply.getText().contains("<get-time-reply><time>"));
		connection=  factory.createQueueConnection(JMS_USER,JMS_PWD);
		connection.start();
	    
	   QueueSession session= connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
	  
	   
	   Queue queue = session.createQueue(JmsConstants.YOUR_QUEUE_NAME);
	  
	   
	    QueueRequestor requestor = new QueueRequestor(session, queue);
	    
	    TextMessage reply = (TextMessage)requestor.request(session.createMessage());
	    
	    Assert.assertTrue(reply.getText().contains("<get-time-reply><time>"));
	    System.out.println(reply.getText());
	    
	}
	
}
