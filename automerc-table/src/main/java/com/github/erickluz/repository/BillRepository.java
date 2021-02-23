package com.github.erickluz.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.github.erickluz.domain.Bill;
import com.github.erickluz.domain.StatusBill;
import com.github.erickluz.dto.BillItemsDTO;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class BillRepository implements PanacheRepository<Bill> {
	
	@Inject
	private BaseDao dao;
	
	public Bill saveOrUpdate(Bill bill) {
		return (Bill) dao.save(bill);
	}
	
	public Bill findById(Long idBill) {
		return findById(idBill);
	}
	
	public BillItemsDTO findFullBillById(Long idBill) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", idBill);
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT new com.github.erickluz.dto.BillItemsDTO(b, bi) FROM Bill b ")
			 .append("		LEFT JOIN FETCH BillItens bi ON ic.bill.idBill = b.idBill ")
			 .append("		WHERE c.idBill = :id");
		
		return (BillItemsDTO) dao.find(query.toString(), params);
	}

	@Transactional
	public void deleteBGillById(Long id) {
		deleteById(id);
	}

	public List<Bill> listBillsByIdUser(Long idUser) {
		return list("FROM Bill c WHERE c.idUser = ?1", idUser);
	}

	public Bill findBillByTableNumber(Integer tableNumber, Long idUser, StatusBill status) {
		Map<String, Object> params = new HashMap<>();
		params.put("tableNumber", tableNumber);
		params.put("idUser", idUser);
		params.put("status", status);
		return find("FROM Bill b WHERE b.tableNumber = :tableNumber AND b.idUser = :idUser AND b.status = :status", params).firstResult();
	}

	public List<Bill> listAllBillsByUserAndStatus(Long idUser, StatusBill status) {
		Map<String, Object> params = new HashMap<>();
		params.put("idUser", idUser);
		params.put("status", status);
		return list("FROM Bill b WHERE b.iduser = :idUser AND c.status = :status", params);
	}
	
	public List<Bill> listAllBillsByUser(Long idUser) {
		Map<String, Object> params = new HashMap<>();
		params.put("idUser", idUser);
		return list("FROM Bill b WHERE b.iduser = :idUser", params);
	}
}
