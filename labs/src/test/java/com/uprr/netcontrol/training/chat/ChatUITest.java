package com.uprr.netcontrol.training.chat;

import static org.junit.Assert.*;

import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.Test;

import com.mockrunner.jms.ConfigurationManager;
import com.mockrunner.jms.DestinationManager;
import com.mockrunner.mock.jms.MockConnection;
import com.mockrunner.mock.jms.MockSession;
import com.tibco.tibjms.admin.TibjmsAdmin;
import com.tibco.tibjms.admin.TibjmsAdminException;

public class ChatUITest {

	@Test
	public void testGetChatQueues() throws TibjmsAdminException {
		TibjmsAdmin tibcoAdmin = new TibjmsAdmin("tcp://omhq169a:7222", "training", "parkfair");
		JmsAdminUtils jmsAdminUtils = new JmsAdminUtils(tibcoAdmin);
		String[] queueNames = jmsAdminUtils.getQueueNames("CHAT.ZACH");
		assertTrue("No queues returned", queueNames.length>0);
		for (String queue : queueNames) {
			if(queue.startsWith("UP.") || queue.startsWith("CHAT.") || queue.equals("ZACH")) {
				fail("Invalid queue name found: " + queue);
			}
		}
	}
	
	@Test
	public void checkMessageCreator() throws Exception {
		String displayName = "Test";
		String myQueueName = "CHAT.TEST";
		String msg = "Test message"; 
		ChatMessageCreator creator = new ChatMessageCreator(msg, displayName, myQueueName);
		MockConnection connection = new MockConnection(new DestinationManager(), new ConfigurationManager());
		Session session = new MockSession(connection, false, Session.AUTO_ACKNOWLEDGE);
		Message message = creator.createMessage(session);
		TextMessage tmsg = (TextMessage)message;
		assertEquals(msg, tmsg.getText());
		assertEquals(displayName, tmsg.getStringProperty("Name"));
		assertEquals(myQueueName, tmsg.getStringProperty("SenderQueue"));
	}

}
