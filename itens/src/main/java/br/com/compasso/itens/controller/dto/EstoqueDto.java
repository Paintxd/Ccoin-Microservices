package br.com.compasso.itens.controller.dto;

import br.com.compasso.itens.model.Estoque;

public class EstoqueDto {

	private Long id;

	private Long idItem;

	private Long idTipoItem;

	private Integer quantiaDisponivel;

	private Integer quantiaReservado;

	public EstoqueDto(Estoque estoque) {
		this.id = estoque.getId();
		this.idItem = estoque.getItem().getId();
		this.idTipoItem = estoque.getTipoItem().getId();
		this.quantiaDisponivel = estoque.getQuantiaDisponivel();
		this.quantiaReservado = estoque.getQuantiaReservado();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdItem() {
		return idItem;
	}

	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}

	public Long getIdTipoItem() {
		return idTipoItem;
	}

	public void setIdTipoItem(Long idTipoItem) {
		this.idTipoItem = idTipoItem;
	}

	public Integer getQuantiaDisponivel() {
		return quantiaDisponivel;
	}

	public void setQuantiaDisponivel(Integer quantiaDisponivel) {
		this.quantiaDisponivel = quantiaDisponivel;
	}

	public Integer getQuantiaReservado() {
		return quantiaReservado;
	}

	public void setQuantiaReservado(Integer quantiaReservado) {
		this.quantiaReservado = quantiaReservado;
	}

}
