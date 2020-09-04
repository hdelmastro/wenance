package com.wenance.business.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wenance.business.model.Average;
import com.wenance.service.ExchangeService;
import com.wenance.service.IExchangeService;

@RestController
@RequestMapping("/wenance")
public class ExchangeController {

	@Autowired
	private IExchangeService service;
	
	private static final DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ISO_LOCAL_DATE_TIME;
	
	@GetMapping("/average")
	public ResponseEntity<Average> average(@RequestParam(value = "from") String fromStr, @RequestParam(value = "to") String toStr) {
	
		
		LocalDateTime fromDate = LocalDateTime.now().minus(60, ChronoUnit.SECONDS);
		if(fromStr != null) {		
			 fromDate = dateTimeFormatter.parse(fromStr, LocalDateTime::from );
		}
				
		LocalDateTime toDate = LocalDateTime.now();
		if(toStr !=  null)  {
			toDate = dateTimeFormatter.parse(toStr, LocalDateTime::from );
		}	
		
		//OptionalDouble avg = service.getAverageBetween(fromDate, toDate);
		//Double body = avg.isPresent()? avg.getAsDouble(): 0d;
		Average body = service.getAverage(fromDate, toDate);
		return new ResponseEntity<Average>(body, HttpStatus.OK);
	}
	
	@GetMapping("/price")
	public ResponseEntity<Double> price(@RequestParam(value = "from") String atDateTimeStr) {
	
		
		LocalDateTime atDateTime = LocalDateTime.now().minus(60, ChronoUnit.SECONDS);
		if(atDateTimeStr != null) {		
			 atDateTime = dateTimeFormatter.parse(atDateTimeStr, LocalDateTime::from );
		}
		
		Double body = service.getPriceByDateTime(atDateTime);
		
		return new ResponseEntity<Double>(body, HttpStatus.OK);
	}
	
}
