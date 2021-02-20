package com.github.erickluz.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Pedido") 
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPedido;
	private long nsuPedido;
	private LocalDateTime dataHoraEntrada;
	private LocalDateTime dataHoraSaida;
	private StatusPedido status;
	private Produto produto;

	public Pedido() {

	}
	public Pedido(long idPedido, long nsuPedido, LocalDateTime dataHoraEntrada, LocalDateTime dataHoraSaida,
			StatusPedido status, Produto produto) {
		this.idPedido = idPedido;
		this.nsuPedido = nsuPedido;
		this.dataHoraEntrada = dataHoraEntrada;
		this.dataHoraSaida = dataHoraSaida;
		this.status = status;
		this.produto = produto;
	}
	public long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}
	public LocalDateTime getDataHoraEntrada() {
		return dataHoraEntrada;
	}
	public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) {
		this.dataHoraEntrada = dataHoraEntrada;
	}
	public LocalDateTime getDataHoraSaida() {
		return dataHoraSaida;
	}
	public void setDataHoraSaida(LocalDateTime dataHoraSaida) {
		this.dataHoraSaida = dataHoraSaida;
	}
	public StatusPedido getStatus() {
		return status;
	}
	public void setStatus(StatusPedido status) {
		this.status = status;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public long getNsuPedido() {
		return nsuPedido;
	}
	public void setNsuPedido(long nsuPedido) {
		this.nsuPedido = nsuPedido;
	}
	
}
