package br.com.compasso.itens.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Estoque implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_item", referencedColumnName = "id")
	private Item item;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tipo_item", referencedColumnName = "id")
	private TipoItem tipoItem;

	private Integer valor;

	private Integer quantiaReservado;
	private Integer quantiaDisponivel;

	public Estoque() {
	}

	public Estoque(Item item, TipoItem tipo, Integer quantiaDisponivel, Integer valor) {
		this.item = item;
		this.tipoItem = tipo;
		this.quantiaDisponivel = quantiaDisponivel;
		this.valor = valor;
		this.quantiaReservado = 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public TipoItem getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(TipoItem tipoItem) {
		this.tipoItem = tipoItem;
	}

	public Integer getQuantiaReservado() {
		return quantiaReservado;
	}

	public void setQuantiaReservado(Integer quantiaReservado) {
		this.quantiaReservado = quantiaReservado;
	}

	public Integer getQuantiaDisponivel() {
		return quantiaDisponivel;
	}

	public void setQuantiaDisponivel(Integer quantiaDisponivel) {
		this.quantiaDisponivel = quantiaDisponivel;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
	public void reserva(Integer quantia) throws Exception {
		if(this.quantiaDisponivel < quantia) {
			throw new Exception();
		} else {
			this.quantiaDisponivel -= quantia;
			this.quantiaReservado += quantia;
		}
	}

}
