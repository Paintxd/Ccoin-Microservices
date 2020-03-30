package br.com.compasso.resgate.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.resgate.controller.dto.StatusResgateDto;
import br.com.compasso.resgate.controller.form.CobrancaForm;
import br.com.compasso.resgate.controller.form.EmailForm;
import br.com.compasso.resgate.controller.form.ResgateForm;
import br.com.compasso.resgate.messaging.RabbitService;
import br.com.compasso.resgate.model.Resgate;
import br.com.compasso.resgate.model.StatusResgate;
import br.com.compasso.resgate.model.TiposStatus;
import br.com.compasso.resgate.repository.ResgateItemRepository;
import br.com.compasso.resgate.repository.ResgateRepository;
import br.com.compasso.resgate.repository.StatusResgateRepository;
import br.com.compasso.resgate.repository.TiposStatusRepository;
import br.com.compasso.resgate.service.CobrancaService;

@CrossOrigin
@RestController
@RequestMapping("/resgate")
public class ResgatesController {

	@Autowired
	private StatusResgateRepository statusResgateRepo;
	
	@Autowired
	private ResgateRepository resgateRepo;
	
	@Autowired
	private TiposStatusRepository tipoStatusRepo;
	
	@Autowired
	private ResgateItemRepository resgateItemRepo;
	
	@Autowired
	private CobrancaService cobra;
	
	@Autowired
	private RabbitService mail;
	
	private Double valorCompra = 0.0;
	
	@PostMapping("/{userId}")
	@Transactional
	public ResponseEntity<StatusResgateDto> resgatar(@RequestBody @Valid List<ResgateForm> form, @PathVariable Long userId, 
			UriComponentsBuilder uriBuilder) {
		try {
			Resgate resgate = new Resgate(userId);
			form.forEach(faz -> {
				resgate.addItem(faz.converter(resgateItemRepo, resgate));
				valorCompra += faz.ReservaItensGetValor();
			});
			
			EmailForm emailForm = cobra.realizaCobranca(new CobrancaForm(userId, valorCompra));
			emailForm.setValor(valorCompra);
			emailForm.setTipo("compra");
			mail.send(emailForm);
			
			resgateRepo.save(resgate);
			
			TiposStatus status = tipoStatusRepo.getOne(Long.valueOf(1));
	
			StatusResgate statusResgate = new StatusResgate(resgate, status);
			statusResgateRepo.save(statusResgate);
			
			URI uri = uriBuilder.path("/resgate/{id}").buildAndExpand(statusResgate.getId()).toUri();
			
			return ResponseEntity.created(uri).body(new StatusResgateDto(statusResgate));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	
}
