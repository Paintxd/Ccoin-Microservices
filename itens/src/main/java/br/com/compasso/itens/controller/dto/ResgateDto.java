package br.com.compasso.itens.controller.dto;

import br.com.compasso.itens.model.Estoque;

public class ResgateDto {

	private String item;
	private Integer valor;

	public ResgateDto() {
	}
	
	public ResgateDto(Estoque estoque) {
		this.item = (estoque.getItem() + " " + estoque.getTipoItem()).toString();
		this.valor = estoque.getValor();
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

}
