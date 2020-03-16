package br.com.compasso.resgate.controller.form;

public class CobrancaForm {

	private Long id;

	private Double valor;
	
	public CobrancaForm() {
	}

	public CobrancaForm(Long id, Double valor) {
		this.id = id;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
