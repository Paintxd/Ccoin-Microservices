package br.com.compasso.resgate.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.compasso.resgate.controller.form.EmailForm;

@Service
public class EmailService {

	public void mail(EmailForm emailForm) {
		RestTemplate client = new RestTemplate();
		HttpEntity<EmailForm> request = new HttpEntity<EmailForm>(emailForm);

		ResponseEntity<HttpStatus> sucesso = client.exchange("http://localhost:8000/mail", HttpMethod.POST, request,
				HttpStatus.class);

		HttpStatus status = sucesso.getBody();
		System.out.println(status);
	}

}
