package org.axle.example.config;

import org.axle.example.cache.ExampleCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.axle")
public class ExampleConfig {
	
	public @Bean ExampleCache cache() {
		return new ExampleCache();
	}
	
}
