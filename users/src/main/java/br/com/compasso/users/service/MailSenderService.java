package br.com.compasso.users.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
	
	public Email con() {
		Email message = new SimpleEmail();
		message.setHostName("smtp.gmail.com");
		message.setSmtpPort(587);
		message.setAuthenticator(new DefaultAuthenticator("joaoterceiro366@gmail.com", "magica123"));
		message.setSSLOnConnect(true);
		return message;
	}
	
	public void destinatarioTransferenciaMail(String destino, String origem, Double valor) throws EmailException {
		Email message = con();
		
		message.setFrom("Ccoin@gmail.com");
		message.addTo(destino);
		message.setSubject("Voce recebeu uma transferencia");
		message.setMsg("Você recebeu uma transferencia no valor de: " + valor + "\nOrigem: " + origem);
		
		message.send();
	}
	
	public void origemTransferenciaMail(String destino, String origem, Double valor) throws EmailException {
		Email message = con();
		
		message.setFrom("Ccoin@gmail.com");
		message.addTo(origem);
		message.setSubject("Voce efetuou uma transferencia");
		message.setMsg("Você efetuou uma transferencia no valor de: " + valor + "\nDestinatario: " + destino);
		
		message.send();
	}
	
	public void compraMail(String comprador, Double valor) throws EmailException {
		Email message = con();
		
		message.setFrom("Ccoin@gmail.com");
		message.addTo(comprador);
		message.setSubject("Voce efetuou uma compra");
		message.setMsg("Voce efetuou uma compra no valor de: " + valor + " visite nosso site para obter mais detalhes");
		
		message.send();
	}
	
}
