package br.com.compasso.resgate.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itens_resgates")
public class ResgateItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long idEstoque;

	private Integer quantidade;

	@ManyToOne(fetch = FetchType.EAGER)
	private Resgate resgate;

	public ResgateItem() {
	}

	public ResgateItem(Long id, Long idEstoque, Integer quantidade, Resgate resgate) {
		this.id = id;
		this.idEstoque = idEstoque;
		this.quantidade = quantidade;
		this.resgate = resgate;
	}

	public ResgateItem(Long idEstoque, Integer quantidade, Resgate resgate) {
		this.idEstoque = idEstoque;
		this.quantidade = quantidade;
		this.resgate = resgate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Resgate getResgate() {
		return resgate;
	}

	public void setResgate(Resgate resgate) {
		this.resgate = resgate;
	}

	public Long getIdEstoque() {
		return idEstoque;
	}

	public void setIdEstoque(Long idEstoque) {
		this.idEstoque = idEstoque;
	}

}
