package com.uprr.netcontrol.training.chat;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class InboundMessageProcessor implements MessageListener {
	
	private ScrollingPanel panel;
	private ChatUI chat;
	
	public InboundMessageProcessor(ScrollingPanel panel, ChatUI chatUI) {
		this.panel = panel;
		this.chat = chatUI;
	}

	@Override
	public void onMessage(Message message) {
		TextMessage tMsg = (TextMessage)message;
		try {
			panel.addMessage(tMsg.getText(), message.getStringProperty("Name"), message.getStringProperty("SenderQueue"));
			chat.changeUser(message.getStringProperty("SenderQueue"));
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
