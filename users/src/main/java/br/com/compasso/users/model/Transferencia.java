package br.com.compasso.users.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transferencias")
public class Transferencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	@JoinColumn(name = "usuario_origem", referencedColumnName = "id")
	private Usuario origem;
	@OneToOne
	@JoinColumn(name = "usuario_destino", referencedColumnName = "id")
	private Usuario destino;

	private Double valor;

	private Date dataTransferencia;

	public Transferencia() {
	}

	public Transferencia(Usuario origem, Usuario destino, Double valor) {
		this.origem = origem;
		this.destino = destino;
		this.valor = valor;
		this.dataTransferencia = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getOrigem() {
		return origem;
	}

	public void setOrigem(Usuario origem) {
		this.origem = origem;
	}

	public Usuario getDestino() {
		return destino;
	}

	public void setDestino(Usuario destino) {
		this.destino = destino;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getDataTransferencia() {
		return dataTransferencia;
	}

	public void setDataTransferencia(Date dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

}
