package com.uprr.netcontrol.training.chat;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;

public class ChatMessageCreator implements MessageCreator {
	
	private final String displayName;
	private final String myQueueName;
	private final String messageText;
	
	public ChatMessageCreator(String messageText, String displayName, String myQueueName) {
		this.messageText = messageText;
		this.displayName = displayName;
		this.myQueueName = myQueueName;
	}

	@Override
	public Message createMessage(Session session) throws JMSException {
		//TODO: create the text message, set 2 string properties - Name=>displayName, SenderQueue=>myQueueName
		return null;
	}

}
