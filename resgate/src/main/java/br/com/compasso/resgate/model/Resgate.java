package br.com.compasso.resgate.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "resgates")
public class Resgate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long idUsuario;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "resgate")
	private List<ResgateItem> itensComprados = new ArrayList<ResgateItem>();

	public Resgate() {
	}

	public Resgate(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Resgate(Long idUsuario, List<ResgateItem> itens) {
		this.idUsuario = idUsuario;
		this.itensComprados = itens;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<ResgateItem> getItensComprados() {
		return itensComprados;
	}

	public void setItensComprados(List<ResgateItem> itensComprados) {
		this.itensComprados = itensComprados;
	}

	public void addItem(ResgateItem resgateItem) {
		this.itensComprados.add(resgateItem);
	}

}
