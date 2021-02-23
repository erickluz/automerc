package com.github.erickluz.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.github.erickluz.commons.Util;
import com.github.erickluz.domain.Bill;
import com.github.erickluz.domain.BillItems;
import com.github.erickluz.domain.StatusBill;

public class BillItemsDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long idBill;
	private Integer tableNumber;
	private String nameClient;
	private String openingTime;
	private String closingTime;
	private BigDecimal amount;
	private Long idUser;
	private StatusBill status;
	private List<BillItems> billItens = new ArrayList<>();

	public BillItemsDTO() {
		
	}

	public BillItemsDTO(Bill bill, List<BillItems> itensComanda){
        this.idBill = bill.getIdBill();
        this.tableNumber = bill.getTableNumber();
        this.nameClient = bill.getNameClient();
		if (bill.getOpeningTime() != null) {
        	this.openingTime = bill.getOpeningTime().format(DateTimeFormatter.ofPattern(Util.DATE_FORMAT));	
        }
        if (bill.getClosingTime() != null) {
        	this.closingTime = bill.getClosingTime().format(DateTimeFormatter.ofPattern(Util.DATE_FORMAT));	
        }
        this.amount = bill.getAmount();
        this.billItens = itensComanda;
        if (bill.getIdUser() != null) {
        	this.idUser = bill.getIdUser();
        }
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

	public String getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}

	public String getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public StatusBill getStatus() {
		return status;
	}

	public void setStatus(StatusBill status) {
		this.status = status;
	}

	public List<BillItems> getBillItens() {
		return billItens;
	}

	public void setBillItens(List<BillItems> billItens) {
		this.billItens = billItens;
	}

}