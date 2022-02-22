package com.uprr.netcontrol.training.chat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.tibco.tibjms.admin.TibjmsAdmin;
import com.uprr.netcontrol.training.chat.ChatUI;
import com.uprr.netcontrol.training.chat.InboundMessageProcessor;


@Configuration
public class ChatConfig {
	@Autowired private JmsTemplate jmsTemplate;
	@Autowired private TibjmsAdmin tibjmsAdmin;
	@Autowired private SingleConnectionFactory singleConnectionFactory;
	
	@Value("${display.name}")
	private String displayName;
	
	@Value("${queue.name}")
	private String queueName;
	
	@Bean
	public ChatUI chatUi() {
		return new ChatUI(displayName, null, null, null, null);
	}
	
	@Bean(name="RequestListenerContainer")
	public DefaultMessageListenerContainer defaultMessageListenerContainer() {
		return null;
	}
	
	private InboundMessageProcessor inboundMessageProcessor() {
		return null;
	}

	

}
