package com.mycode.ccs;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mycode.ccs.Bean.CurrencyConversionBean;

//@FeignClient(name="curr-xchange-services", url="http://localhost:8001")
//@FeignClient(name = "curr-xchange-services")
@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "curr-xchange-services")
public interface CurrencyXChangeServiceFeignProxy {
	//@GetMapping("curr-xchange/from/{from}/to/{to}")
	@GetMapping("curr-xchange-services/curr-xchange/from/{from}/to/{to}")
	public CurrencyConversionBean retriebveXchangeValue(@PathVariable String from, @PathVariable String to);

}
	