package com.github.erickluz.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.github.erickluz.domain.Stock;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class StockRepository implements PanacheRepository<Stock> {

	@Inject
	BaseDao dao;
	
	public Stock saveOrUpdate(Stock stock) {
		return (Stock) dao.saveOrUpdate(stock);
	}
}
