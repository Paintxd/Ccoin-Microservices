package br.com.compasso.users.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.users.controller.dto.UsuarioCobradoDto;
import br.com.compasso.users.controller.form.CobrancaForm;
import br.com.compasso.users.model.Usuario;
import br.com.compasso.users.repository.UsuarioRepository;

@CrossOrigin
@RestController
@RequestMapping("/cobranca")
public class CobrancaController {

	@Autowired
	private UsuarioRepository userRepo;
	
	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioCobradoDto> cobranca(@RequestBody CobrancaForm form) {
		try {

			Usuario usuario = userRepo.getOne(form.getId());
			usuario.Saca(form.getValor());

			return ResponseEntity.ok(new UsuarioCobradoDto(usuario));
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		
	}
}
