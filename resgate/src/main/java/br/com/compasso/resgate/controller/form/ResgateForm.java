package br.com.compasso.resgate.controller.form;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.compasso.resgate.model.Resgate;
import br.com.compasso.resgate.model.ResgateItem;
import br.com.compasso.resgate.repository.ResgateItemRepository;

public class ResgateForm {

	private Long idEstoque;
	private Integer quantidade;

	public ResgateForm() {
	}
	
	public ResgateForm(Long idEstoque, Integer quantidade) {
		this.idEstoque = idEstoque;
		this.quantidade = quantidade;
	}

	public Long getIdEstoque() {
		return idEstoque;
	}

	public void setIdEstoque(Long idEstoque) {
		this.idEstoque = idEstoque;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public ResgateItem converter(ResgateItemRepository resgateItemRepo, Resgate resgate) {
		try {
			ResgateItem resgateItem = new ResgateItem(this.idEstoque, quantidade, resgate);
			
			resgateItemRepo.save(resgateItem);
			
			return resgateItem;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Double ReservaItensGetValor() {
		RestTemplate client = new RestTemplate();
		HttpEntity<ResgateForm> request = new HttpEntity<ResgateForm>(new ResgateForm(this.idEstoque, this.quantidade));
		
		ResponseEntity<Double> sucesso = client.exchange("http://localhost:8082/estoque/reservado", HttpMethod.PUT, request, Double.class);
		
		Double valor = sucesso.getBody();
		
		return Double.valueOf(valor * this.quantidade);
	}
	
}
