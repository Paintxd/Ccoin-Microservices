package br.com.compasso.users.controller.dto;

import java.util.Date;

import br.com.compasso.users.model.Usuario;

public class TransferenciaDto {

	private String destino;
	private String origem;
	private Double valor;
	private Date data_transferencia;

	public TransferenciaDto(Usuario origem, Usuario destino, Double valor) {
		this.destino = destino.getNome();
		this.origem = origem.getNome();
		this.valor = valor;
		this.data_transferencia = new Date();
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getData_transferencia() {
		return data_transferencia;
	}

	public void setData_transferencia(Date data_transferencia) {
		this.data_transferencia = data_transferencia;
	}
	
}
