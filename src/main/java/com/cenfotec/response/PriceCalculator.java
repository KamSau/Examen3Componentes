package com.cenfotec.response;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.cenfotec.config.ItemConfig;

@Component
public class PriceCalculator {

	private final ItemConfig itemConfig;

	public PriceCalculator(ItemConfig itemConfig) {
		this.itemConfig = itemConfig;
	}

	public BigDecimal calculatePrice(String item, int quantity) {
		BigDecimal finalPrice = itemConfig.getItem().getOrDefault(item.toLowerCase(), BigDecimal.ZERO);
		return finalPrice.multiply(new BigDecimal(quantity));

	}
	
}
