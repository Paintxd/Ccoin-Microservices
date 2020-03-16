package br.com.compasso.itens.controller.dto;

import br.com.compasso.itens.model.Item;

public class ItemDto {

	private Long id;
	private String descricao;

	public ItemDto(Item item) {
		this.id = item.getId();
		this.descricao = item.getDescricao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
