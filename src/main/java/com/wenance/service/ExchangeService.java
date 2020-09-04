package com.wenance.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.OptionalDouble;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.wenance.business.model.Average;
import com.wenance.business.model.Exchange;
import com.wenance.config.CexConnectionConfig;
import com.wenance.persistence.entity.Quotation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ExchangeService implements IExchangeService {
	
	@Autowired
	private IQuotationService quotationService;
	
	@Autowired
	private WebTarget webTarget;
		
	@Scheduled(fixedRate = 10000)
	private void getExchangePrice() {
		
		LocalDateTime initTime = LocalDateTime.now();
		log.info("Init scheduled method at: {} " , initTime);
		
		Response response = webTarget.request().get();
		
		Exchange exchange = response.readEntity(Exchange.class);
		
		log.info("The value of exchange is: {} ", exchange);
		Quotation quotation = new Quotation();
		quotation.setPrice(exchange.getLprice());
		quotation.setCreationDate(LocalDateTime.now());
				
		quotationService.save(quotation);
		
		LocalDateTime endTime = LocalDateTime.now();

		log.info("Finish scheduled method at: {} and it take: {} seconds", endTime, Duration.between(initTime, endTime).getSeconds());
		
		
	}
	
	private OptionalDouble getAverageBetweenDateTime(LocalDateTime from, LocalDateTime to, List<Quotation> quotations ){	
				
		OptionalDouble average = 
				quotations
				.stream()
				.filter(q -> q.getCreationDate().isAfter(from) && q.getCreationDate().isBefore( to))
				.map(r -> r.getPrice())
				.mapToDouble(Double::doubleValue)
				.average();
		
		return average ;		
		
	}

	public Double getPriceByDateTime(LocalDateTime atDateTime) {
		
		List<Quotation> quotations = quotationService.getQuotation();
		
		Double price = 
				quotations
					.stream()
					.filter(q -> q.getCreationDate().isAfter(atDateTime.minus(10500, ChronoUnit.MILLIS)) && q.getCreationDate().isBefore(atDateTime))
					.findFirst()
					.orElseThrow(()-> new RuntimeException("Price not found") )
					.getPrice();
		
		return price;
	}
	
	private OptionalDouble getMax(List<Quotation> quotations ) {
				
		OptionalDouble max =quotations
				.stream()
				.map(r -> r.getPrice())
				.mapToDouble(Double::doubleValue)
				.max();	
		return max;
	}
	
	
	private Long getPercentageDiferenceBetween (OptionalDouble max, OptionalDouble avg) {
		
		log.info("Max value: {} ", max);
		log.info("Avg value: {} ", avg);
		
		Double result = ((avg.orElseThrow(()-> new RuntimeException("The average can't be null"))/max.orElseThrow(()-> new RuntimeException("The max can't be null"))) * 100);
		
		return Math.round(result);
	}
	
	
	public Average getAverage(LocalDateTime from, LocalDateTime to) {
		
		List<Quotation> quotations = quotationService.getQuotation();		
		OptionalDouble average = getAverageBetweenDateTime(from, to, quotations);
		Long percentageDiference = getPercentageDiferenceBetween( getMax(quotations),average );
		
		Average averageResponse =  Average.builder()
				.average(average.getAsDouble())
				.perDiff(percentageDiference)
				.build();
		
		return averageResponse;
		
	}

}
