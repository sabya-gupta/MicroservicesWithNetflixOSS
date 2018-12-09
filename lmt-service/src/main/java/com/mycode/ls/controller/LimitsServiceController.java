package com.mycode.ls.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycode.ls.AppConfig;
import com.mycode.ls.bean.LimitConfiguration;

@RestController
public class LimitsServiceController {

	@Autowired
	AppConfig config;
	
	@GetMapping(path="/hello")
	public String sayHello() {
		return "hello!";
	}
	
	@GetMapping("/limits")
	public LimitConfiguration getLimitsConfig() {
		return new LimitConfiguration(config.getMin(), config.getMax());
	}
}
