package br.com.compasso.users.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.compasso.users.model.Cargo;
import br.com.compasso.users.model.NivelAcesso;
import br.com.compasso.users.model.Unidade;
import br.com.compasso.users.model.Usuario;
import br.com.compasso.users.repository.CargoRepository;
import br.com.compasso.users.repository.NivelAcessoRepository;
import br.com.compasso.users.repository.UnidadeRepository;

public class UsuarioForm {

	@NotNull
	@NotEmpty
	private String email;
	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String login;
	@NotNull
	@NotEmpty
	private String nome;
	@NotNull
	private Boolean responsavel_unidade;
	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String senha;

	@NotNull
	private Long unidade;

	@NotNull
	private Long descricaoAcesso;

	@NotNull
	private Long descricaoCargo;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getDescricaoAcesso() {
		return descricaoAcesso;
	}

	public void setDescricaoAcesso(Long descricaoAcesso) {
		this.descricaoAcesso = descricaoAcesso;
	}

	public Long getDescricaoCargo() {
		return descricaoCargo;
	}

	public void setDescricaoCargo(Long descricaoCargo) {
		this.descricaoCargo = descricaoCargo;
	}

	public Long getUnidade() {
		return unidade;
	}

	public void setUnidade(Long unidade) {
		this.unidade = unidade;
	}

	public Usuario converter(CargoRepository cargoRepo, NivelAcessoRepository nAcessoRepo,
			UnidadeRepository unidadeRepo) {

		NivelAcesso nivelAcesso = nAcessoRepo.getOne(descricaoAcesso);
		Cargo cargo = cargoRepo.getOne(descricaoCargo);
		Unidade idUnidade = unidadeRepo.getOne(unidade);

		return new Usuario(email, login, nome, responsavel_unidade, new BCryptPasswordEncoder().encode(senha),
				nivelAcesso, cargo, idUnidade);
	}

}