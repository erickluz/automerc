package com.github.erickluz.domain;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Bill")
public class Bill implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idBill;
    public Integer tableNumber;
    public String nameClient;
    public LocalDateTime openingTime;
    public LocalDateTime closingTime;
    public BigDecimal amount;
    @Enumerated(EnumType.ORDINAL)
    public StatusBill status;
    public Long idUser;

    public Bill() {
        this.openingTime = LocalDateTime.now();
    }

    public Bill(Integer tableNumber, String nameClient, BigDecimal amount){
    	this.tableNumber = tableNumber;
    	this.nameClient = nameClient;
    	this.openingTime = LocalDateTime.now();
    	this.amount = amount;
    }

	public Long getIdBill() {
		return idBill;
	}

	public void setIdBill(Long idBill) {
		this.idBill = idBill;
	}

	public Integer getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(Integer tableNumber) {
		this.tableNumber = tableNumber;
	}

	public String getNameClient() {
		return nameClient;
	}

	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}

	public LocalDateTime getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(LocalDateTime openingTime) {
		this.openingTime = openingTime;
	}

	public LocalDateTime getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(LocalDateTime closingTime) {
		this.closingTime = closingTime;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public StatusBill getStatus() {
		return status;
	}

	public void setStatus(StatusBill status) {
		this.status = status;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	
}