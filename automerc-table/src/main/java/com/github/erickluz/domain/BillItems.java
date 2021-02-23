package com.github.erickluz.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="BillItems") 
public class BillItems implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idBillItems;
    public Integer quantity;
	@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idBill")
    public Bill bill;
    public Long idProduct;

    public BillItems(){

    }

	public Long getIdBillItems() {
		return idBillItems;
	}

	public void setIdBillItems(Long idBillItems) {
		this.idBillItems = idBillItems;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}
}