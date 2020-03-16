package br.com.compasso.resgate.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.compasso.resgate.controller.form.CobrancaForm;

@Service
public class CobrancaService {

	public void realizaCobranca(CobrancaForm cobranca) {
		RestTemplate client = new RestTemplate();
		HttpEntity<CobrancaForm> request = new HttpEntity<CobrancaForm>(cobranca);
		

		ResponseEntity<String> sucesso =  client.exchange("http://localhost:8080/cobranca", HttpMethod.POST, request, String.class);
		
		String resposta = sucesso.getBody();
		
		System.out.println(resposta + " <------");
	}

}