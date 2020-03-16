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

import br.com.compasso.itens.controller.dto.EstoqueDto;
import br.com.compasso.itens.controller.dto.ResgateDto;
import br.com.compasso.itens.controller.form.EstoqueForm;
import br.com.compasso.itens.controller.form.QuantiaEstoqueForm;
import br.com.compasso.itens.model.Estoque;
import br.com.compasso.itens.repository.EstoqueRepository;
import br.com.compasso.itens.repository.ItensRepository;
import br.com.compasso.itens.repository.TipoItemRepository;

@CrossOrigin
@RestController
@RequestMapping("/estoque")
public class EstoqueController {

	@Autowired
	private EstoqueRepository estoqueRepo;
	
	@Autowired
	private ItensRepository itensRepo;
	
	@Autowired
	private TipoItemRepository tipoRepo;
	
	@GetMapping
	public List<Estoque> listaEstoque() {
		List<Estoque> estoque = estoqueRepo.findAll();
		return estoque;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResgateDto> resgateRequest(@PathVariable Long id) {
		Estoque estoque = estoqueRepo.getOne(id);
		
		return ResponseEntity.ok(new ResgateDto(estoque));
	}
	
	
	@PostMapping
	public ResponseEntity<EstoqueDto> cadastroEstoque(@RequestBody @Valid EstoqueForm form, UriComponentsBuilder uriBuilder) {
		try {
			Estoque estoque = form.converte(estoqueRepo, itensRepo, tipoRepo);
			estoqueRepo.save(estoque);
			
			URI uri = uriBuilder.path("/estoque/{id}").buildAndExpand(estoque.getId()).toUri();

			return ResponseEntity.created(uri).body(new EstoqueDto(estoque));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> removeItem(@PathVariable Long id) {
		Optional<Estoque> optional = estoqueRepo.findById(id);
		
		if (optional.isPresent()) {
			estoqueRepo.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/disponivel")
	@Transactional
	public ResponseEntity<EstoqueDto> updateEstoqueDisponivel(@RequestBody @Valid QuantiaEstoqueForm form) {
		Optional<Estoque> optional = estoqueRepo.findById(form.getIdEstoque());

		if (optional.isPresent()) {
			Estoque estoque = estoqueRepo.getOne(form.getIdEstoque());
			
			estoque.setQuantiaDisponivel(estoque.getQuantiaDisponivel() + form.getQuantidade());
			
			return ResponseEntity.ok(new EstoqueDto(estoque));
		}

		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/reservado")
	@Transactional
	public ResponseEntity<?> updateEstoqueReservado(@RequestBody @Valid QuantiaEstoqueForm form) {
		Optional<Estoque> optional = estoqueRepo.findById(form.getIdEstoque());

		if (optional.isPresent()) {
			Estoque estoque = estoqueRepo.getOne(form.getIdEstoque());
			
			estoque.setQuantiaDisponivel(estoque.getQuantiaDisponivel() - form.getQuantidade());
			estoque.setQuantiaReservado(estoque.getQuantiaReservado() + form.getQuantidade());

			return ResponseEntity.ok(new Double(estoque.getValor()));
		}

		return ResponseEntity.notFound().build();
	}
	
}
