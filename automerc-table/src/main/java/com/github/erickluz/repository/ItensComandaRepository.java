package com.github.erickluz.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.github.erickluz.domain.BillItems;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class ItensComandaRepository implements PanacheRepository<BillItems>{

	@Inject
	private BaseDao dao;
	
    public List<BillItems> findItemsByIdBill(Long idBill) {
    	return list("FROM BillItems bi WHERE bi.bill.idBill = ?1", idBill);
    }

    public BillItems findItemByProductAndBill(Long idProduct, Long idBill) {
    	return find("FROM BillItems bi WHERE bi.idProduct = :idProduct AND bi.bill.idBill = :idBill", 
    			Parameters.with("idProduct", idProduct),
    			Parameters.with("idBill", idBill))
    			.firstResult();
    }

    public void deleteItemsByBill(Long idProduct) {
    	delete("FROM BillItems bi WHERE bi.bill.idBill = ?1", idProduct);
    }
    
	public Long findItemsByProduct(Long idProduct) {
		return count("FROM BillItems bi WHERE bi.idProduct = ?1", idProduct);
	}
	
	public BillItems saveOrUpdate(BillItems item) {
		return (BillItems) dao.save(item);
	}
	
}