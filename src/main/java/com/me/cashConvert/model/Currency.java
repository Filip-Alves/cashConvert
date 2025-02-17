package com.me.cashConvert.model;

import java.math.BigDecimal;
import java.util.Map;

import lombok.Data;

@Data
public class Currency {
	// same property https://api.frankfurter.dev/v1/latest
	private BigDecimal amount;
	private String base;
	private String data;
	private Map<String, BigDecimal> rates;
}
