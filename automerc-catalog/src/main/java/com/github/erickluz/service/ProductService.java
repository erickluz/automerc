package com.github.erickluz.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.github.erickluz.domain.Product;
import com.github.erickluz.repository.ProductRepository;

@ApplicationScoped
public class ProductService {

	@Inject
	ProductRepository dao;
	
	public Product saveOrUpdate(Product product) {
		return dao.saveOrUpdate(product);
	}
	
	public void delete(Long idProduct) {
		Product product = dao.findById(idProduct);
		dao.delete(product);
	}
	
	public Product findById(Long idProduct) {
		return dao.findById(idProduct);
	}
	
	public List<Product> findByName(String name, Long idUser) {
		return dao.findByNameAndIdUser(name, idUser);
	}
}
