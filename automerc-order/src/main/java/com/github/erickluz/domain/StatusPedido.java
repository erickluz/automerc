package com.github.erickluz.domain;

import java.util.HashMap;
import java.util.Map;

public enum StatusPedido {
	NA_FILA		((short) 1, "Na fila de preparo"),
	EM_PREPARO	((short) 2, "Em preparo"),
	PREPARADO	((short) 3, "Preparado"),
	A_CAMINHO	((short) 4, "A caminho"),
	ENTREGUE	((short) 5, "Entregue"),
	CANCELADO	((short) 6, "Cancelado");
	
	private short codigo;
	private String descricao;
	private static Map<Short, StatusPedido> statusPedido = new HashMap<>();
	
	static {
		for (StatusPedido status : values()) {
			statusPedido.put(status.getCodigo(), status);
		}
	}
	
	StatusPedido(short codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public short getCodigo() {
		return codigo;
	}

	public void setCodigo(short codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static StatusPedido fromCodigo(short codigo) {
		return statusPedido.get(codigo);
	}
}
