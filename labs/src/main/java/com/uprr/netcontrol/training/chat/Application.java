package com.uprr.netcontrol.training.chat;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.uprr.netcontrol.training.chat.config.MainConfig;

public class Application {

	public static void main(String[] args) {
		System.setProperty("uprr.implementation.environment", "dev");
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
		final DefaultMessageListenerContainer jmsListenerContainer = context.getBean("RequestListenerContainer", DefaultMessageListenerContainer.class);

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() { 
			@Override
			public void run() {
				jmsListenerContainer.shutdown();
			}
		}));
	}
}
