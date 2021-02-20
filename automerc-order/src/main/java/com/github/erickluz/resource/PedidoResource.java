package com.github.erickluz.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.github.erickluz.domain.Pedido;
import com.github.erickluz.domain.StatusPedido;
import com.github.erickluz.service.PedidoService;

import io.netty.handler.codec.http.HttpResponseStatus;

@ApplicationScoped
@Path("/pedidos")
public class PedidoResource {
	
	@Inject
	private PedidoService service;
	
	@POST
	@Path("/inserir")
	public Response inserirPedido(Pedido pedido) {
		Pedido entidadePedido = service.inserirPedido(pedido);
		return (pedido != null) ? Response.ok(entidadePedido).build() : Response.serverError().build();
	}
	
	@GET
	@Path("/status")
	public Response status(@PathParam("nsuPedido") Long nsuPedido) {
		StatusPedido status = service.obterStatusPedido(nsuPedido);
		return (status != null) ? Response.ok(status).build() : Response.status(HttpResponseStatus.NOT_FOUND.code()).build();
	}
	
	@PUT
	@Path("/cancelar")
	public Response cancelarPedido(Integer nsuPedido) {
		service.cancelarPedido(null);
		return Response.noContent().build();
	}
}
