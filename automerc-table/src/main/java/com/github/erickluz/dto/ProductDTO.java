package com.github.erickluz.dto;

import java.math.BigDecimal;

public class ProductDTO {

	private Long idProduct;
	private BigDecimal value;
	public Long getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
}
