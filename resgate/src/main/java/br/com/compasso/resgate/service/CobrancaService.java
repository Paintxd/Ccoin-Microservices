package br.com.compasso.resgate.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.compasso.resgate.controller.form.CobrancaForm;
import br.com.compasso.resgate.controller.form.EmailForm;

@Service
public class CobrancaService {

	public EmailForm realizaCobranca(CobrancaForm cobranca) {
		RestTemplate client = new RestTemplate();
		HttpEntity<CobrancaForm> request = new HttpEntity<CobrancaForm>(cobranca);

		ResponseEntity<EmailForm> sucesso =  client.exchange("http://localhost:8080/cobranca", HttpMethod.POST, request, EmailForm.class);
		
		EmailForm mail = sucesso.getBody();
		
		return mail;
	}

}