package br.com.compasso.resgate.controller.dto;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.compasso.resgate.model.ResgateItem;

public class ResgateItemDto {

	private String itens;

	private Integer valor;

	private Integer quantia;

	public ResgateItemDto(ResgateItem resgate) {
		popula(resgate);
		this.quantia = resgate.getQuantidade();
	}
	
	public String getItens() {
		return itens;
	}

	public void setItens(String itens) {
		this.itens = itens;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public Integer getQuantia() {
		return quantia;
	}

	public void setQuantia(Integer quantia) {
		this.quantia = quantia;
	}
	
	public void popula(ResgateItem resgate) {
		RestTemplate client = new RestTemplate();
		
		ResponseEntity<EstoqueDto> sucesso =  client.exchange("http://localhost:8082/estoque/"+resgate.getIdEstoque(), HttpMethod.GET, null, EstoqueDto.class);
		
		EstoqueDto estoque = sucesso.getBody();
		
		this.itens = estoque.getItem().toString();
		this.valor = estoque.getValor() * resgate.getQuantidade();
	}

}
