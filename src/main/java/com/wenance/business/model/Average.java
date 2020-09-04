package com.wenance.business.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Average {
	
	private Double average;
	private Long perDiff;
}
