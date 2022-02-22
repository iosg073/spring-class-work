package com.uprr.netcontrol.training.chat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;


@Configuration
public class PropertyConfig {
	@Autowired private Environment environment;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		configurer.setIgnoreUnresolvablePlaceholders(false);
		configurer.setIgnoreResourceNotFound(false);
		configurer.setLocalOverride(true);
		return configurer;
	}

	public String getProperty(String propertyName) {
		return environment.getProperty(propertyName);
	}

	public <T> T getProperty(String propertyName, Class<T> clazz) {
		return environment.getProperty(propertyName, clazz);
	}

}
