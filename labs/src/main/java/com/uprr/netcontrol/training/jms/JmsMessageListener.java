package com.uprr.netcontrol.training.jms;

import java.util.Date;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class JmsMessageListener implements MessageListener {
	
	private Session session = null;
	
	private static final String REPLY = "<get-time-reply><time> .............." + new Date() + "</time></get-time-reply>";

	public JmsMessageListener (Session session) {
		this.session = session;
	}
	
	@Override
	public void onMessage(Message message) {
		System.out.println("Got: " + message);
		try {
			Destination destination =message.getJMSReplyTo();
			
			if(destination== null) {
				System.out.println("Reply to: "+ destination);
				MessageProducer producer=session.createProducer(destination);
				producer.send(session.createTextMessage(REPLY));
			
			}
			MessageProducer producer = session.createProducer(destination);
			TextMessage replyMessage = session.createTextMessage(REPLY);
			producer.send(replyMessage);


			} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}

}
