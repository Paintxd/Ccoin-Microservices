package br.com.compasso.users.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.users.controller.dto.UsuarioDto;
import br.com.compasso.users.controller.form.UpdateUsuarioForm;
import br.com.compasso.users.controller.form.UsuarioForm;
import br.com.compasso.users.model.Usuario;
import br.com.compasso.users.repository.CargoRepository;
import br.com.compasso.users.repository.NivelAcessoRepository;
import br.com.compasso.users.repository.UnidadeRepository;
import br.com.compasso.users.repository.UsuarioRepository;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class CriacaoController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CargoRepository cargoRepo;
	
	@Autowired
	private NivelAcessoRepository nAcessoRepo;
	
	@Autowired
	private UnidadeRepository unidadeRepo;
	
	@GetMapping
	public Page<UsuarioDto> listaUsuarios(@RequestParam(required = false) String nomeUsuario,
			@PageableDefault(sort="nivelAcesso", direction=Direction.ASC, page=0, size=10) Pageable paginacao) {
		
		if(nomeUsuario == null) {
			Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
			return UsuarioDto.converter(usuarios);	
		} else {
			Page<Usuario> usuarios = usuarioRepository.findByNome(nomeUsuario, paginacao);
			return UsuarioDto.converter(usuarios);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDto> listaId(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		if(usuario.isPresent()) {
			return ResponseEntity.ok(new UsuarioDto(usuario.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	 
	@PostMapping("/cadastro")
	@Transactional
	public ResponseEntity<UsuarioDto> cadastro(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {
		Usuario usuario = form.converter(cargoRepo, nAcessoRepo, unidadeRepo);
		usuarioRepository.save(usuario);
		
		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remove(@PathVariable Long id) {
		Optional<Usuario> optional = usuarioRepository.findById(id);
		
		if(optional.isPresent()) {
			usuarioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDto> update(@PathVariable Long id, @RequestBody @Valid UpdateUsuarioForm form) {
		Optional<Usuario> optional = usuarioRepository.findById(id);
		
		if(optional.isPresent()) {
			Usuario usuario = form.update(id, cargoRepo, nAcessoRepo, unidadeRepo, usuarioRepository);
			return ResponseEntity.ok(new UsuarioDto(usuario));
		}
		
		return ResponseEntity.notFound().build();
	}
	
}