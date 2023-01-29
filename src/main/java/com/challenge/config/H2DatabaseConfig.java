package com.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;

@Configuration
@EnableR2dbcRepositories
public class H2DatabaseConfig extends AbstractR2dbcConfiguration {

	@Override
	public ConnectionFactory connectionFactory() {
		return new H2ConnectionFactory(H2ConnectionConfiguration.builder().url("mem:mydb").build());
	}

	@Bean
	public ConnectionFactoryInitializer initializer() {
		var initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory());
		return initializer;
	}
}