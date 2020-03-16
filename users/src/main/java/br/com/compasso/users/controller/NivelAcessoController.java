package br.com.compasso.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.users.model.NivelAcesso;
import br.com.compasso.users.repository.NivelAcessoRepository;

@CrossOrigin
@RestController
@RequestMapping("/niveisAcesso")
public class NivelAcessoController {

	@Autowired
	private NivelAcessoRepository acessoRepo;
	
	@GetMapping
	public ResponseEntity<?> niveisAcessox() {
		List<NivelAcesso> niveis = acessoRepo.findAll();
		
		return ResponseEntity.ok(niveis);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> nivelAcesso(@PathVariable Long id) {
		return ResponseEntity.ok(acessoRepo.findById(id));
	}
	
}