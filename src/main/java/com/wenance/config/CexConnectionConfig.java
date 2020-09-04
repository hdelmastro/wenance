package com.wenance.config;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;

@Configuration
public class CexConnectionConfig {


	@Value("${source.api.base.url}")
	private String URL_BASE_API;
    @Value("${source.api.path}")
	private String EXCHANGE_TYPE;
    
    @Bean
	public WebTarget target() {
		return ClientBuilder.newClient(new ClientConfig().register(Jackson2JsonDecoder.class)).target(URL_BASE_API).path(EXCHANGE_TYPE);
	}
}
