package com.mycode.ces.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycode.ces.bean.CurrencyXchangeValue;

public interface CurrencyXchageValueRepository extends JpaRepository<CurrencyXchangeValue, Long>{

	CurrencyXchangeValue findByFromAndTo(String from, String to);
}
