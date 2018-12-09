package com.mycode.ccs.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mycode.ccs.CurrencyXChangeServiceFeignProxy;
import com.mycode.ccs.Bean.CurrencyConversionBean;

@RestController
public class CurrConvController {
	
	@Autowired
	private CurrencyXChangeServiceFeignProxy xchangeProxy;

	@Value("${server.port}")
	private int port;
	
	@GetMapping("/hello")
	public String sayhello() {
		return "Hello From Currency Converter Service";
	}
	
	@GetMapping("/curr-conv/feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean getCurrencyConversionFC(@PathVariable String from, 
			@PathVariable String to, @PathVariable BigDecimal quantity) {
		
		CurrencyConversionBean convResponse = xchangeProxy.retriebveXchangeValue(from, to);
		
		return new CurrencyConversionBean(convResponse.getId(), from, to, quantity, 
				convResponse.getConversionMultiple(), quantity.multiply(convResponse.getConversionMultiple()), 
				convResponse.getPort());
	}

	
	@GetMapping("/curr-conv/restclient/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean getCurrencyConversionRC(@PathVariable String from, 
			@PathVariable String to, @PathVariable BigDecimal quantity) {
		
		Map<String, String> uriMaps = new HashMap<>();
		uriMaps.put("from", from);
		uriMaps.put("to", to);
		
		RestTemplate restClinet = new RestTemplate();
		ResponseEntity<CurrencyConversionBean> responseEntity = restClinet.getForEntity("http://localhost:8001/curr-xchange/from/{from}/to/{to}", 
				CurrencyConversionBean.class, 
				uriMaps);
		CurrencyConversionBean convResponse = responseEntity.getBody();
		
		return new CurrencyConversionBean(convResponse.getId(), from, to, quantity, 
				convResponse.getConversionMultiple(), quantity.multiply(convResponse.getConversionMultiple()), 
				convResponse.getPort());
	}
}
