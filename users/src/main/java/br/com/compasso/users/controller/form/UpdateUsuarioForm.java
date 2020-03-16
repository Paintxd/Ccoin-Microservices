package br.com.compasso.users.controller.form;

import javax.validation.constraints.NotNull;

import br.com.compasso.users.model.Cargo;
import br.com.compasso.users.model.NivelAcesso;
import br.com.compasso.users.model.Unidade;
import br.com.compasso.users.model.Usuario;
import br.com.compasso.users.repository.CargoRepository;
import br.com.compasso.users.repository.NivelAcessoRepository;
import br.com.compasso.users.repository.UnidadeRepository;
import br.com.compasso.users.repository.UsuarioRepository;

public class UpdateUsuarioForm {
	
	@NotNull
	private String email;
	@NotNull
	private String nome;
	@NotNull
	private Boolean responsavel_unidade;
	@NotNull
	private Long idUnidade;
	@NotNull
	private Long idAcesso;
	@NotNull
	private Long idCargo;

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

	public Long getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(Long idUnidade) {
		this.idUnidade = idUnidade;
	}

	public Long getIdAcesso() {
		return idAcesso;
	}

	public void setIdAcesso(Long idAcesso) {
		this.idAcesso = idAcesso;
	}

	public Long getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(Long idCargo) {
		this.idCargo = idCargo;
	}

	public Usuario update(Long id, CargoRepository cargoRepo, NivelAcessoRepository nAcessoRepo,
			UnidadeRepository unidadeRepo, UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.getOne(id);
		
		Cargo cargo = cargoRepo.getOne(this.idCargo);
		usuario.setCargo(cargo);
		Unidade unidade = unidadeRepo.getOne(this.idUnidade);
		usuario.setUnidade(unidade);
		NivelAcesso nivelAcesso = nAcessoRepo.getOne(this.idAcesso);
		usuario.setNivelAcesso(nivelAcesso);
		
		usuario.setEmail(this.email);
		usuario.setNome(this.nome);
		usuario.setResponsavelUnidade(this.responsavel_unidade);

		return usuario;
	}

}
