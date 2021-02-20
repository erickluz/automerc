package com.github.erickluz.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.github.erickluz.domain.Pedido;
import com.github.erickluz.domain.StatusPedido;
import com.github.erickluz.repository.PedidoRepository;

@ApplicationScoped
public class PedidoService {
	
	@Inject
	private PedidoRepository dao;
	
	public Pedido inserirPedido(Pedido pedido) {
		dao.persist(pedido);
		return new Pedido();
	}
	
	public StatusPedido obterStatusPedido(Long nsuPedido) {
		return dao.obterStatusPedido(nsuPedido);
	}
	
	public void cancelarPedido(Long nsuPedido) {
		dao.cancelarPedido(nsuPedido);
	}
}
