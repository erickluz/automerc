package com.github.erickluz.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.github.erickluz.domain.Stock;
import com.github.erickluz.repository.StockRepository;

@ApplicationScoped
public class StockService {

	@Inject
	StockRepository dao;
	
	public Stock saveOrUpdate(Stock Stock) {
		return dao.saveOrUpdate(Stock);
	}
	
	public void delete(Long idStock) {
		Stock Stock = dao.findById(idStock);
		dao.delete(Stock);
	}
	
	public Stock findById(Long idStock) {
		return dao.findById(idStock);
	}
	
}
