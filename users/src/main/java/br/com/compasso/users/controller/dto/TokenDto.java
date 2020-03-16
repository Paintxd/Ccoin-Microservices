package br.com.compasso.users.controller.dto;

public class TokenDto {

	private String token;
	private String tipo;
	private Long userId;

	public TokenDto(String token, String tipo, Long id) {
		this.token = token;
		this.tipo = tipo;
		this.userId = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
