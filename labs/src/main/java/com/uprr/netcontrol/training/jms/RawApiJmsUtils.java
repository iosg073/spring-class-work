package com.uprr.netcontrol.training.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;

public class RawApiJmsUtils implements JmsUtils {

	private QueueConnection connection;
	private QueueConnectionFactory factory;
	
	public RawApiJmsUtils(String serverUrl, String username, String password) throws JMSException {
		factory = new com.tibco.tibjms.TibjmsQueueConnectionFactory(serverUrl);
		connection= (QueueConnection) factory.createConnection(username,password);
	
		
	}
	
	@Override
	public void postTextMessage(String queueName, String messageBody) throws JMSException {
		Session session= connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//Destination queue=session.createQueue(queueName);
		Queue queue=session.createQueue(queueName);
		MessageProducer msgProducer= session.createProducer(queue);
		TextMessage msg=session.createTextMessage(messageBody);
		msg.setStringProperty("Waheed", "Alam");
		msgProducer.send(msg);
		session.close();
	}

	@Override
	public Message getMessage(String queueName) throws JMSException {
		Session session= connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue=session.createQueue(queueName);
		MessageConsumer messageConsummer= session.createConsumer(queue);
		connection.start();
		//Message msg=null;
		
		Message msg= messageConsummer.receive(5000);
//		assertNotNull(msg);
		session.close();
		return msg;
	}
	
	

}
