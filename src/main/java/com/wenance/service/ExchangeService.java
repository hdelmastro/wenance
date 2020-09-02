package com.wenance.service;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.wenance.business.model.Exchange;

@Service
public class ExchangeService {
	private static final String URL_BASE_API = "https://cex.io/";
    private static final String EXCHANGE_TYPE = "api/last_price/BTC/USD";
    
	public Configuration getConfiguration() {
		Configuration configuration = new ClientConfig().register(Jackson2JsonDecoder.class);
		return  configuration;
	}
	
	public Client getClient() {
		
		Client client = ClientBuilder.newClient(getConfiguration());
		return client;
	}
	
	@Scheduled(fixedRate = 10000)
	public void getExchangePrice() {
		
		LocalDateTime initTime = LocalDateTime.now();
		System.out.println("Init scheduled method at : "+initTime);
		
		WebTarget webTarget = getClient().target(URL_BASE_API).path(EXCHANGE_TYPE);
		
		Response response = webTarget.request().get();
		
		Exchange objResponse = response.readEntity(Exchange.class);
		
		System.out.println("el exchange es: "+ objResponse.getLprice());
		LocalDateTime finTime = LocalDateTime.now();
		System.out.println("Finish scheduled method at : "+finTime);
		System.out.println("and take this time to finish : "+ Duration.between(initTime, finTime).getSeconds());
		
	}

}
