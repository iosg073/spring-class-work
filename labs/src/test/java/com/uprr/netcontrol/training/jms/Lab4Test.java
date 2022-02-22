package com.uprr.netcontrol.training.jms;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;

import org.junit.Test;

import com.uprr.netcontrol.training.jms.util.JmsConstants;

public class Lab4Test {

	private static final String JMS_URL = "omhq169a:7222";
	private static final String JMS_USER = "training";
	private static final String JMS_PWD = "parkfair";
	
	private QueueConnectionFactory factory=new com.tibco.tibjms.TibjmsQueueConnectionFactory(JMS_URL);;
	private Connection connection;
	public void sendSampleMessage() throws Exception {
		RawApiJmsUtils jmsUtils = new RawApiJmsUtils(JMS_URL, JMS_USER, JMS_PWD);
		jmsUtils.postTextMessage(JmsConstants.YOUR_QUEUE_NAME, "Sample ........................................................................test message");
	}
	
	@Test
	
	public void lab4Test() throws Exception {
		sendSampleMessage();
		
    connection=  factory.createConnection(JMS_USER,JMS_PWD);
    
    Session session= connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    connection.start();
    Queue queue=session.createQueue(JmsConstants.YOUR_QUEUE_NAME);
    MessageConsumer messageConsummer= session.createConsumer(queue);
    MessageListener messageListner= new JmsMessageListener(session);
    Message msg= messageConsummer.receive();
   // messageListner.onMessage(msg);
    messageConsummer.setMessageListener(messageListner);

		//your code here
		Thread.sleep(100);
	}
	
}
