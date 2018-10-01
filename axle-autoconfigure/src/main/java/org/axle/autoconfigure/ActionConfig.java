package org.axle.autoconfigure;

import org.axle.core.DefaultTypeParser;
import org.axle.core.TypeParser;
import org.axle.core.deserialize.TypeDeserializeChain;
import org.axle.core.formatter.Formatter;
import org.axle.core.formatter.JsonFormatter;
import org.axle.core.web.ActionDispatcherServlet;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;

/**
 * axle-autoconfigure
 * <description>configuration</description>
 * @author Johnny Liu
 * @date 2018-10-01
 */
@Configuration
@ConditionalOnClass(ActionDispatcherServlet.class)
public class ActionConfig implements ApplicationContextAware{
	
	private ApplicationContext applicationContext;
	
	@Bean
	@ConditionalOnMissingBean(ActionDispatcherServlet.class)
	public ActionDispatcherServlet constomDispatcherServlet() {
		return new ActionDispatcherServlet((WebApplicationContext) this.applicationContext);
	}
	
	public @Bean ServletRegistrationBean<ActionDispatcherServlet> servletRegistrationBean() {
		return new ServletRegistrationBean<>(constomDispatcherServlet(),"/");
	}

	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		if(this.applicationContext == null) this.applicationContext = arg0;
	}
	
	public @Bean TypeParser typeParser() {
		return new DefaultTypeParser();
	}
	
	public @Bean TypeDeserializeChain typeDeserializeChain() {
		return new TypeDeserializeChain();
	}
	
	public @Bean Formatter format() {
		return new JsonFormatter();
	}
}
