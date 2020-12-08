package com.cenfotec.config;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "pricing")
public class ItemConfig {
	private HashMap<String, BigDecimal> item;

	public HashMap<String, BigDecimal> getItem() {
		return item;
	}

	public void setItem(HashMap<String, BigDecimal> item) {
		this.item = item;
	}
}
