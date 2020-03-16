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

import br.com.compasso.itens.controller.dto.ItemDto;
import br.com.compasso.itens.controller.form.ItemForm;
import br.com.compasso.itens.model.Item;
import br.com.compasso.itens.repository.ItensRepository;

@CrossOrigin
@RestController
@RequestMapping("/itens")
public class ItensController {

	@Autowired
	private ItensRepository itensRepo;

	@GetMapping
	public List<Item> listaItens() {
		List<Item> itens = itensRepo.findAll();
		return itens;
	}

	@PostMapping("/cadastro")
	public ResponseEntity<ItemDto> cadastroItem(@RequestBody @Valid ItemForm form, UriComponentsBuilder uriBuilder) {
		Item item = form.converte();
		itensRepo.save(item);

		URI uri = uriBuilder.path("/itens/{id}").buildAndExpand(item.getId()).toUri();

		return ResponseEntity.created(uri).body(new ItemDto(item));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> removeItem(@PathVariable Long id) {
		Optional<Item> optional = itensRepo.findById(id);
		
		if (optional.isPresent()) {
			itensRepo.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ItemDto> updateItem(@RequestBody @Valid ItemForm form, @PathVariable Long id) {
		Optional<Item> optional = itensRepo.findById(id);

		if (optional.isPresent()) {
			Item item = form.update(id, itensRepo);
			return ResponseEntity.ok(new ItemDto(item));
		}

		return ResponseEntity.notFound().build();
	}
	
}
