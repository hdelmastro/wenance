package com.wenance.persistence.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wenance.persistence.entity.Quotation;

public interface IQuotationDao extends CrudRepository<Quotation, Long> {
	
	
	List<Quotation> findByCreationDateBetween(LocalDateTime from, LocalDateTime to);

}
