package com.github.erickluz.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.github.erickluz.domain.BillItems;
import com.github.erickluz.exception.ObjectNotFoundException;
import com.github.erickluz.repository.ItensComandaRepository;

@ApplicationScoped
public class ItemsBillService {

    @Inject
    ItensComandaRepository dao;

    public List<BillItems> findItemsByIdBill(Long idBill){
        return dao.findItemsByIdBill(idBill);
    }

    public BillItems saveOrUpdate(BillItems itemComanda) {
        return dao.saveOrUpdate(itemComanda);
    }

    public void save(List<BillItems> itemsBill) {
        dao.persist(itemsBill);
    }

    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    public BillItems findById(Long id) throws ObjectNotFoundException {
    	BillItems billItems = dao.findById(id);
    	if (billItems == null) {
    		throw new ObjectNotFoundException("Item not found");
    	}
    	return billItems;
    }

    public BillItems findItemByProductAndBill(Long idProduct, Long idBill) throws ObjectNotFoundException {
        BillItems item = dao.findItemByProductAndBill(idProduct, idBill);
        if (item == null) {
        	throw new ObjectNotFoundException(null);
        }
        return item;
    }
    
    public boolean isProductInSomeBill(Long idProduct) {
    	Long quantityBills = dao.findItemsByProduct(idProduct);
    	return (quantityBills.compareTo(0L) > 0);
    }

    public void deleteItemsByBill(Long idBill) {
        dao.deleteItemsByBill(idBill);
    }
}