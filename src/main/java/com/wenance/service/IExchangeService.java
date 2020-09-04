package com.wenance.service;

import java.time.LocalDateTime;

import com.wenance.business.model.Average;

public interface IExchangeService {

	Double getPriceByDateTime(LocalDateTime atDateTime);

	Average getAverage(LocalDateTime from, LocalDateTime to);

}
