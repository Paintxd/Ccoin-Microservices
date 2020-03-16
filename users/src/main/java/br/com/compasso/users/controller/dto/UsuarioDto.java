package br.com.compasso.users.controller.dto;

import org.springframework.data.domain.Page;

import br.com.compasso.users.model.Usuario;

public class UsuarioDto {

	private String email;
	private String nome;
	private Boolean responsavel_unidade;
	private String cidade;
	private String uf;
	private String endereco;
	private String descricaoAcesso;
	private String descricaoCargo;
	private Double credito;
	
	public UsuarioDto(Usuario usuario) {
		this.email = usuario.getEmail();
		this.nome = usuario.getNome();
		this.responsavel_unidade = usuario.getResponsavelUnidade();
		this.cidade = usuario.getUnidade().getCidade();
		this.endereco = usuario.getUnidade().getCidade();
		this.uf = usuario.getUnidade().getUf();
		this.descricaoAcesso = usuario.getNivelAcesso().getDescricaoAcesso();
		this.descricaoCargo = usuario.getCargo().getDescricaoCargo();
		this.credito = usuario.getCredito();
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
	public Boolean getResponsavel_unidade() {
		return responsavel_unidade;
	}
	public void setResponsavel_unidade(Boolean responsavel_unidade) {
		this.responsavel_unidade = responsavel_unidade;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getDescricaoAcesso() {
		return descricaoAcesso;
	}
	public void setDescricaoAcesso(String descricaoAcesso) {
		this.descricaoAcesso = descricaoAcesso;
	}
	public String getDescricaoCargo() {
		return descricaoCargo;
	}
	public void setDescricaoCargo(String descricaoCargo) {
		this.descricaoCargo = descricaoCargo;
	}
	public Double getCredito() {
		return credito;
	}
	public void setCredito(Double credito) {
		this.credito = credito;
	}

	public static Page<UsuarioDto> converter(Page<Usuario> usuarios) {
		return usuarios.map(UsuarioDto::new);
	}
	
}
