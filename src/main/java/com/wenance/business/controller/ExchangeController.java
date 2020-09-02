package com.wenance.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wenance.service.ExchangeService;

@RestController
public class ExchangeController {

	@Autowired
	private ExchangeService service;
	
	@GetMapping("/greeting")
	public ResponseEntity<String> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
	
		
		service.getExchangePrice();
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
}
