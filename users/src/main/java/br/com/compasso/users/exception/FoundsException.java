package br.com.compasso.users.exception;

public class FoundsException extends Exception {
	private static final long serialVersionUID = 1L;

	private static final String mesage = "Fundos insuficientes para a operação";
	
	public FoundsException() {
		super(mesage);
	}
}
