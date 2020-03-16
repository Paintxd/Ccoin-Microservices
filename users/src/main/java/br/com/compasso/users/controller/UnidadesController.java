package br.com.compasso.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.users.model.Unidade;
import br.com.compasso.users.repository.UnidadeRepository;

@CrossOrigin
@RestController
@RequestMapping("/unidades")
public class UnidadesController {

	@Autowired
	private UnidadeRepository unidadesRepo;
	
	@GetMapping
	public ResponseEntity<?> unidades() {
		List<Unidade> unidades = unidadesRepo.findAll();
		
		return ResponseEntity.ok(unidades);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> unidade(@PathVariable Long id) {
		return ResponseEntity.ok(unidadesRepo.findById(id));
	}
	
}
