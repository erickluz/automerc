package com.github.erickluz.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Stock")
public class Stock implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long idStock;
	@ManyToOne
	@JoinColumn(name = "idProduto", nullable = false)
	private Product product;
	private Integer cfgQtdMin;
	private Long idProvider;
	public Long getIdStock() {
		return idStock;
	}
	public void setIdStock(Long idStock) {
		this.idStock = idStock;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getCfgQtdMin() {
		return cfgQtdMin;
	}
	public void setCfgQtdMin(Integer cfgQtdMin) {
		this.cfgQtdMin = cfgQtdMin;
	}
	public Long getIdProvider() {
		return idProvider;
	}
	public void setIdProvider(Long idProvider) {
		this.idProvider = idProvider;
	}
}
