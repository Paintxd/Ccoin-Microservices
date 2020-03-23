package br.com.compasso.users.controller.dto;

import br.com.compasso.users.model.Usuario;

public class UsuarioCobradoDto {

	private String email;
	private String nome;

	public UsuarioCobradoDto(Usuario usuario) {
		this.email = usuario.getEmail();
		this.nome = usuario.getNome();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
