package br.com.compasso.users.controller.form;

import br.com.compasso.users.model.Transferencia;

public class EmailForm {

	private String email;
	private String nome;
	private Double valor;
	private String tipo;
	private String destinatario;

	public EmailForm(Transferencia transferencia) {
		this.email = transferencia.getOrigem().getEmail();
		this.nome = transferencia.getOrigem().getNome();
		this.valor = transferencia.getValor();
		this.tipo = "transferencia";
		this.destinatario = transferencia.getDestino().getNome();
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

}
