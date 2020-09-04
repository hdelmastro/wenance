package com.wenance.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenance.persistence.dao.IQuotationDao;
import com.wenance.persistence.entity.Quotation;

@Service
public class QuotationService implements IQuotationService {
	
	private static final int TIME_AT_TIME_GAP = 30;
	@Autowired
	private IQuotationDao quotationDao;

	@Override
	public List<Quotation> getQuotation() {
		return (List<Quotation>) quotationDao.findAll();
	}
	
	@Override
	public Quotation getAtDateTime(LocalDateTime atDateTime) {
		LocalDateTime from =  atDateTime.minus(TIME_AT_TIME_GAP, ChronoUnit.SECONDS);
		List<Quotation> quotations = quotationDao.findByCreationDateBetween(from, atDateTime);
		return quotations.isEmpty() ? null : quotations.get(quotations.size() -1);
	}
	
	public void save(Quotation entity) {
		quotationDao.save(entity);
	}

	

}
