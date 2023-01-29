package com.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;

/*
CREATE TABLE users(id int auto_increment PRIMARY KEY, username varchar, password varchar)

INSERT INTO users(username, password) VALUES("user", "1234");
*/

@Configuration
@EnableR2dbcRepositories
public class H2DatabaseConfig extends AbstractR2dbcConfiguration {

	@Bean
	@Override
	public ConnectionFactory connectionFactory() {
		return H2ConnectionFactory.inMemory("testdb");
	}

	@Bean
	public ConnectionFactoryInitializer initializer() {
		var initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory());
		initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("db.sql")));
		return initializer;
	}
}