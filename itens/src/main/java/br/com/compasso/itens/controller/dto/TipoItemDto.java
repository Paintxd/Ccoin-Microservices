package br.com.compasso.itens.controller.dto;

import br.com.compasso.itens.model.TipoItem;

public class TipoItemDto {
	
	private Long id;
	private String descricao;

	public TipoItemDto(TipoItem tipo) {
		this.id = tipo.getId();
		this.descricao = tipo.getDescricao();
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
