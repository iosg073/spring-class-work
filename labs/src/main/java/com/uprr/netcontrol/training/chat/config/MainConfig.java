package com.uprr.netcontrol.training.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import({
	JMSConfig.class,
	ChatConfig.class
})
public class MainConfig {

	
}
