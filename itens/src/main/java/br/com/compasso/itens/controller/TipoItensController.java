package br.com.compasso.itens.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.itens.controller.dto.TipoItemDto;
import br.com.compasso.itens.controller.form.TipoItemForm;
import br.com.compasso.itens.model.TipoItem;
import br.com.compasso.itens.repository.TipoItemRepository;

@CrossOrigin
@RestController
@RequestMapping("/tipoItem")
public class TipoItensController {
	
	@Autowired
	private TipoItemRepository tipoRepo;
	
	@GetMapping
	public List<TipoItem> listaItens() {
		List<TipoItem> tipos = tipoRepo.findAll();
		return tipos;
	}

	@PostMapping("/cadastro")
	public ResponseEntity<TipoItemDto> cadastroItem(@RequestBody @Valid TipoItemForm form, UriComponentsBuilder uriBuilder) {
		TipoItem tipo = form.converte();
		tipoRepo.save(tipo);

		URI uri = uriBuilder.path("/tipoItem/{id}").buildAndExpand(tipo.getId()).toUri();

		return ResponseEntity.created(uri).body(new TipoItemDto(tipo));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> removeItem(@PathVariable Long id) {
		Optional<TipoItem> optional = tipoRepo.findById(id);

		if (optional.isPresent()) {
			tipoRepo.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TipoItemDto> updateItem(@RequestBody @Valid TipoItemForm form, @PathVariable Long id) {
		Optional<TipoItem> optional = tipoRepo.findById(id);

		if (optional.isPresent()) {
			TipoItem tipo = form.update(id, tipoRepo);
			return ResponseEntity.ok(new TipoItemDto(tipo));
		}

		return ResponseEntity.notFound().build();
	}

	
}
