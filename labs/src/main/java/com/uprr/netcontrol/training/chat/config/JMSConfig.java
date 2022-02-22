package com.uprr.netcontrol.training.chat.config;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;

import com.tibco.tibjms.admin.TibjmsAdmin;
import com.tibco.tibjms.admin.TibjmsAdminException;

@Configuration
@PropertySource(value = { "classpath:application.properties",
    "classpath:application-${uprr.implementation.environment}.properties" })
public class JMSConfig {

  @Value("${jms.initialContextFactory}")
  private String namingFactory;

  @Value("${jms.url}")
  private String url;

  @Value("${jms.user}")
  private String user;

  @Value("${jms.password}")
  private String password;

  @Value("${jms.receive.connection.factory}")
  private String factory;
  
  @Bean("tibcoJmsAdmin")
  public TibjmsAdmin tibjmsAdmin() throws TibjmsAdminException  {
	  return new TibjmsAdmin(url, user, password);
  }
  
  @Bean("jmsTemplate")
  public JmsTemplate jmsTemplate() throws IllegalArgumentException, NamingException {
	  return null;
  }

  @Bean("singleConnectionFactory")
  public SingleConnectionFactory singleConnectionFactory() throws IllegalArgumentException, NamingException {

	SingleConnectionFactory cachingCF = new SingleConnectionFactory();
    cachingCF.setReconnectOnException(true);
    cachingCF.setTargetConnectionFactory(getAuthConnectionFactory());
    return cachingCF;
  }

  private UserCredentialsConnectionFactoryAdapter getAuthConnectionFactory()
      throws IllegalArgumentException, NamingException {
    UserCredentialsConnectionFactoryAdapter adapter = new UserCredentialsConnectionFactoryAdapter();
    adapter.setUsername(user);
    adapter.setPassword(password);
    adapter.setTargetConnectionFactory(getBaseConnectionFactory());
    return adapter;
  }

  private ConnectionFactory getBaseConnectionFactory() throws IllegalArgumentException, NamingException {
    Properties jndiEnv = new Properties();
    jndiEnv.setProperty("java.naming.factory.initial", namingFactory);
    jndiEnv.setProperty("java.naming.provider.url", url);
    jndiEnv.setProperty("java.naming.security.principal", user);
    jndiEnv.setProperty("java.naming.security.credentials", password);

    JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
    jndiObjectFactoryBean.setJndiEnvironment(jndiEnv);
    jndiObjectFactoryBean.setJndiName(factory);
    jndiObjectFactoryBean.afterPropertiesSet();
    return (ConnectionFactory) jndiObjectFactoryBean.getObject();
  }
}
