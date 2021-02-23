package com.github.erickluz.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.github.erickluz.commons.Util;
import com.github.erickluz.domain.Bill;
import com.github.erickluz.dto.BillItemsDTO;
import com.github.erickluz.exception.AuthorizationException;
import com.github.erickluz.exception.DataIntegrityException;
import com.github.erickluz.exception.ObjectNotFoundException;
import com.github.erickluz.service.BillService;

import io.netty.handler.codec.http.HttpResponseStatus;

@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/comandas")
public class BillResource {

	@Inject
	BillService service;
	@Inject
	Util util;

	@GET
	@Path(value = "/usuarios/{idUsuario}")
	public Response listAllBillsByUser(@PathParam("idUsuario") final Long idUsuario) {
		List<Bill> comandas = service.listAllBillsByUser(idUsuario);
		List<BillItemsDTO> comandasDTO = comandas.stream().map(c -> new BillItemsDTO(c, null)).collect(Collectors.toList());
		return (!comandas.isEmpty()) ? Response.ok(comandasDTO).build() : Response.noContent().build();
	}

	@GET
	@Path(value = "/ativas/{idUsuario}")
	public Response listAllBillsOpendByUser(@PathParam("idUser") Long idUser) {
		List<Bill> bills = service.listAllOpenedBillsByUser(idUser);
		List<BillItemsDTO> comandasDTO = bills.stream().map(c -> new BillItemsDTO(c, null)).collect(Collectors.toList());
		return (!bills.isEmpty()) ? Response.ok(comandasDTO).build() : Response.noContent().build();
	}

	@GET
	@Path(value = "/fechadas/{idUsuario}")
	public Response listarTodasComandasFechadasPorUsuario(@PathParam("idUser") Long idUser) {
		List<Bill> bills = service.listAllBillsClosedByUser(idUser);
		List<BillItemsDTO> billsDTO = bills.stream().map(c -> new BillItemsDTO(c, null)).collect(Collectors.toList());
		return (!bills.isEmpty()) ? Response.ok(billsDTO).build() : Response.noContent().build();
	}

	@GET
	@Path(value = "/{id}")
	public Response buscarComandaPorId(@PathParam("id") Long id) throws ObjectNotFoundException {
		BillItemsDTO comanda = service.findFullBillById(id);
		return (comanda != null) ? Response.ok(comanda).build() : Response.status(HttpResponseStatus.NOT_FOUND.code()).build();
	}

	@POST
	public Response salvarComanda(BillItemsDTO comandaItens) throws DataIntegrityException, ObjectNotFoundException, AuthorizationException {
		Bill bill = service.salvarComanda(comandaItens);
		URI uri = UriBuilder.fromPath(util.getUrlServidor() + bill.getIdBill()).build();
		return Response.created(uri).build();
	}

	@PUT
	public Response editarComanda(BillItemsDTO comanda) throws DataIntegrityException, ObjectNotFoundException {
		service.editBill(comanda);
		return Response.noContent().build();
	}

	@PATCH
	public Response acrescentarItemsEmUmaComanda(BillItemsDTO comanda) throws DataIntegrityException, ObjectNotFoundException {
		service.acrescentarItensEmUmaComanda(comanda);
		return Response.noContent().build();
	}

	@DELETE
	@Path(value = "/{id}/status")
	public Response fecharComanda(@PathParam("{id}") Long id) throws DataIntegrityException, ObjectNotFoundException {
		service.fecharComanda(id);
		return Response.noContent().build();
	}

	@DELETE
	@Path(value = "/{id}")
	public Response removerComanda(@PathParam("{id}") Long id) {
		service.deleteBill(id);
		return Response.noContent().build();
	}
}