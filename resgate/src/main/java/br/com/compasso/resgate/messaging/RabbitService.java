package br.com.compasso.resgate.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.compasso.resgate.controller.form.EmailForm;

public class RabbitService {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private Queue queue;

	public void send(EmailForm emailForm) {

		this.template.convertAndSend(queue.getName(), emailForm);
	}

	
}
