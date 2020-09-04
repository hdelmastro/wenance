package com.wenance.service;

import java.time.LocalDateTime;
import java.util.List;

import com.wenance.persistence.entity.Quotation;

public interface IQuotationService {

	public List<Quotation> getQuotation();
	public void save(Quotation entity);
	public Quotation getAtDateTime(LocalDateTime atDateTime);

}
