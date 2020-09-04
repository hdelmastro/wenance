package com.wenance.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import com.wenance.business.model.Average;
import com.wenance.persistence.entity.Quotation;


@SpringBootTest
class ExchangeServiceTest {
	
	@Mock
	private QuotationService quotationService;
	
	@Mock
	private WebTarget webTarget;
	
	@InjectMocks
	private IExchangeService target =  new ExchangeService();
	
	private LocalDateTime setupDate = LocalDateTime.of(2020,9, 2, 15, 0);
	
	
	@BeforeEach
	private  void  setup() {
		Builder request = Mockito.mock(Builder.class);
		Response response = Mockito.mock(Response.class);
		Mockito.when(request.get()).thenReturn(response);
		Mockito.when(webTarget.request()).thenReturn(request);
		
		List<Quotation> quotations = new ArrayList<Quotation>();		
		
		for(int i = 0; i<10; i++) {
			Quotation quotation = new Quotation();
			quotation.setCreationDate(setupDate.plus(i*10, ChronoUnit.SECONDS));
			quotation.setPrice(new Double(i)*10);
			quotations.add(quotation );
		}
				
		Mockito.when(quotationService.getQuotation()).thenReturn(quotations);
	}
	
	
	@Test
	void testGetPriceByDateTime() {
		Double result0 = target.getPriceByDateTime(setupDate.plus(1, ChronoUnit.SECONDS));
		assertEquals(0d, result0);
		Double result1 = target.getPriceByDateTime(setupDate.plus(11, ChronoUnit.SECONDS));
		assertEquals(10d, result1);
		Double result2 = target.getPriceByDateTime(setupDate.plus(21, ChronoUnit.SECONDS));
		assertEquals(20d, result2);
		Double result3 = target.getPriceByDateTime(setupDate.plus(31, ChronoUnit.SECONDS));
		assertEquals(30d, result3);
		Double result4 = target.getPriceByDateTime(setupDate.plus(41, ChronoUnit.SECONDS));
		assertEquals(40d, result4);
		Double result5 = target.getPriceByDateTime(setupDate.plus(51, ChronoUnit.SECONDS));
		assertEquals(50d, result5);
		Double result6 = target.getPriceByDateTime(setupDate.plus(61, ChronoUnit.SECONDS));
		assertEquals(60d, result6);
		Double result7 = target.getPriceByDateTime(setupDate.plus(71, ChronoUnit.SECONDS));
		assertEquals(70d, result7);
		Double result8 = target.getPriceByDateTime(setupDate.plus(81, ChronoUnit.SECONDS));
		assertEquals(80d, result8);
		Double result9 = target.getPriceByDateTime(setupDate.plus(91, ChronoUnit.SECONDS));
		assertEquals(90d, result9);
		
	}

	@Test
	void testGetAverage() {
		Average avg = target.getAverage(setupDate.minus(1,ChronoUnit.SECONDS), setupDate.plus(91,ChronoUnit.SECONDS));
		assertEquals(45d,avg.getAverage());
		assertEquals(50, avg.getPerDiff());
	}

}
