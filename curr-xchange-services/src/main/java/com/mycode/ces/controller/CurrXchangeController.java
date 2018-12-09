package com.mycode.ces.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mycode.ces.bean.CurrencyXchangeValue;
import com.mycode.ces.repository.CurrencyXchageValueRepository;

@RestController
public class CurrXchangeController {
	
	@Autowired
	CurrencyXchageValueRepository repository;
	
	@Value("${server.port}")
	private String port;

	@GetMapping("/sayhello")
	public String sayhello() {
		return "hello from curr xchange";
	}
	
	@GetMapping("/curr-xchange/from/{from}/to/{to}")
	public CurrencyXchangeValue retriebveXchangeValue(@PathVariable String from, 
			@PathVariable String to) {
		CurrencyXchangeValue currXVal = repository.findByFromAndTo(from, to);
		currXVal.setPort(Integer.parseInt(port));
		 return currXVal;
	}
}
