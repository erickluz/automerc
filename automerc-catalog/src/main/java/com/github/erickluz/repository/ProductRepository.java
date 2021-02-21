package com.github.erickluz.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.github.erickluz.domain.Product;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

	@Inject
	BaseDao dao;
	
	public Product saveOrUpdate(Product product) {
		return (Product) dao.saveOrUpdate(product);
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> findByNameAndIdUser(String name, Long idUser) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("name", name);
		parameters.put("idUser", idUser);
		return (List<Product>)(Product) dao.list("SELECT p FROM Product p WHERE p.name like '%:name%' AND idUser = :idUser", parameters);
	}
	
}
