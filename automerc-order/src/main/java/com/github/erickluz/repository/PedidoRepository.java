package com.github.erickluz.repository;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.github.erickluz.domain.Pedido;
import com.github.erickluz.domain.StatusPedido;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido>{

	@Inject
	private BaseDao dao;
	
	public void find(Pedido pedido) {
		persist(pedido);
	}
	
	public StatusPedido obterStatusPedido(long nsuPedido) {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("nsuPedido", nsuPedido);
		Short codigoStatusPedido = (Short) dao.find("SELECT p.status FROM Pedido p WHERE p.nsuPedido = ?1", parametros);
		return StatusPedido.fromCodigo(codigoStatusPedido);
	}

	public void cancelarPedido(Long nsuPedido) {
		update("status = 1? WHERE nsuPedido = ?2 ", StatusPedido.CANCELADO, nsuPedido);
	}
}
