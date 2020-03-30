package br.com.compasso.users.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.users.controller.dto.TransferenciaDto;
import br.com.compasso.users.controller.form.EmailForm;
import br.com.compasso.users.controller.form.TransferenciaForm;
import br.com.compasso.users.messaging.RabbitService;
import br.com.compasso.users.model.Transferencia;
import br.com.compasso.users.model.Usuario;
import br.com.compasso.users.repository.TransferenciaRepository;
import br.com.compasso.users.repository.UsuarioRepository;

@RestController
@RequestMapping("/transferir")
public class TransferenciaController {

	@Autowired
	private UsuarioRepository userRepo;
	
	@Autowired
	private TransferenciaRepository transfRepo;
	
	@Autowired
	private RabbitService mail;
	
	@PostMapping
	@Transactional
	public ResponseEntity<TransferenciaDto> transfere(@RequestBody TransferenciaForm dadosTransf) {
		try {
			Usuario origem = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Usuario origemJpa = userRepo.findByNome(origem.getNome());
			origemJpa.Saca(dadosTransf.getValor());
			
			Usuario destino = userRepo.findByNome(dadosTransf.getNomeDestinatario());
			destino.Deposita(dadosTransf.getValor());

			Transferencia transferencia = new Transferencia(origem, destino, dadosTransf.getValor());
			transfRepo.save(transferencia);
			
			mail.send(new EmailForm(transferencia));
			
			return ResponseEntity.ok(new TransferenciaDto(origem, destino, dadosTransf.getValor()));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
