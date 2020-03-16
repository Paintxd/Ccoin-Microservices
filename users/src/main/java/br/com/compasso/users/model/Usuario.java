package br.com.compasso.users.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.compasso.users.exception.FoundsException;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String login;
	private String email;
	private String senha;
	private String nome;
	private Double credito;

	@ManyToOne
	@JoinColumn(name = "id_nivel_acesso", referencedColumnName = "id")
	private NivelAcesso nivelAcesso;

	@ManyToOne
	@JoinColumn(name = "id_cargo", referencedColumnName = "id")
	private Cargo cargo;

	@ManyToOne
	@JoinColumn(name = "id_unidade", referencedColumnName = "id")
	private Unidade unidade;

	@Column(name = "responsavel_unidade")
	private Boolean responsavelUnidade;

	public Usuario() {
	}

	public Usuario(String email, String login, String nome, Boolean responsavel_unidade, String senha,
			NivelAcesso nivelAcesso, Cargo cargo, Unidade unidade, Double credito) {
		this.email = email;
		this.login = login;
		this.nome = nome;
		this.responsavelUnidade = responsavel_unidade;
		this.senha = senha;
		this.nivelAcesso = nivelAcesso;
		this.cargo = cargo;
		this.unidade = unidade;
		this.credito = credito;
	}

	public Usuario(String email, String login, String nome, Boolean responsavel_unidade, String senha,
			NivelAcesso nivelAcesso, Cargo cargo, Unidade unidade) {
		this.email = email;
		this.login = login;
		this.nome = nome;
		this.responsavelUnidade = responsavel_unidade;
		this.senha = senha;
		this.nivelAcesso = nivelAcesso;
		this.cargo = cargo;
		this.unidade = unidade;
		this.credito = new Double(0);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getCredito() {
		return credito;
	}

	public void setCredito(Double credito) {
		this.credito = credito;
	}

	public NivelAcesso getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(NivelAcesso nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Boolean getResponsavelUnidade() {
		return responsavelUnidade;
	}

	public void setResponsavelUnidade(Boolean responsavelUnidade) {
		this.responsavelUnidade = responsavelUnidade;
	}

	public void Saca(Double valor) throws FoundsException {
		System.out.println(valor + "     " + this.credito);
		if (this.credito >= valor) {
			System.out.println("i was here hehe ---->" + this.credito);
			this.credito -= valor;
			System.out.println("i was here hehe ---->" + this.credito);
		} else {
			throw new FoundsException();
		}
	}

	public void Deposita(Double valor) {
		this.credito += valor;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<NivelAcesso> niveisAcessoList = new ArrayList<NivelAcesso>();
		niveisAcessoList.add(this.nivelAcesso);
		return niveisAcessoList;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}