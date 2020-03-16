package br.com.compasso.itens.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.compasso.itens.model.TipoItem;
import br.com.compasso.itens.repository.TipoItemRepository;

public class TipoItemForm {

	@NotEmpty
	@NotNull
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoItem converte() {
		return new TipoItem(this.descricao);
	}

	public TipoItem update(Long id, TipoItemRepository tipoRepo) {
		TipoItem tipo = tipoRepo.getOne(id);
		tipo.setDescricao(descricao);
		
		return tipo;
	}
	
}
