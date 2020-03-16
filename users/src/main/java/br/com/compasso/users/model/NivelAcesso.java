package br.com.compasso.users.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "niveis_acesso")
public class NivelAcesso implements GrantedAuthority {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao")
	private String descricaoAcesso;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricaoAcesso() {
		return descricaoAcesso;
	}
	public void setDescricao(String descricaoAcesso) {
		this.descricaoAcesso = descricaoAcesso;
	}
	
	@Override
	public String getAuthority() {
		return this.descricaoAcesso;
	}

}